package trivia;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static trivia.PlayGame.readYesNo;

public class Game implements IGame {

    private final ArrayList<Player> players = new ArrayList<>();
    private final Map<Categories, LinkedList<String>> questions = new EnumMap<>(Categories.class);

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (Categories category : Categories.values()) {
            LinkedList<String> categoryQuestions = new LinkedList<>();
            String fileName = category.name().toLowerCase() + ".properties";
            Properties properties = new Properties();

            try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
                if (input == null) {
                    System.err.println("Could not load " + fileName);
                    continue;
                }
                properties.load(input);
                for (String key : properties.stringPropertyNames()) {
                    categoryQuestions.add(properties.getProperty(key));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            questions.put(category, categoryQuestions);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() < 2);
    }

    public boolean addPlayer(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return false;
            }
        }
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player player = players.get(currentPlayer);
        System.out.println(player + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (player.getInPenaltyBox()) {
            isGettingOutOfPenaltyBox = roll % 2 != 0;
            System.out.println(player + (isGettingOutOfPenaltyBox ? " is" : " is not") + " getting out of the penalty box");
        }
        if (!player.getInPenaltyBox() || isGettingOutOfPenaltyBox) {
            jouer(roll, player);
        }
    }

    private void jouer(int roll, Player player) {
        player.setPlayerPosition(player.getPlayerPosition() + roll);
        if (player.getPlayerPosition() > 12)
            player.setPlayerPosition(player.getPlayerPosition() - 12);
        System.out.println(player
                + "'s new location is "
                + player.getPlayerPosition());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    void askQuestion() {
        System.out.println(questions.get(currentCategory()).removeFirst());
    }

    private Categories currentCategory() {
        int position = players.get(currentPlayer).getPlayerPosition() - 1;
        int nbCat = Categories.values().length;
        int categoryIndex = position % nbCat;
        return Categories.values()[categoryIndex];
    }

    public boolean handleCorrectAnswer() {
        Player player = players.get(currentPlayer);
        boolean res;
        player.addSuccessfulAttempts();
        if (player.getInPenaltyBox() && !isGettingOutOfPenaltyBox) {
            res = true;
        } else {
            res = getWinner(player);
        }
        currentPlayer++;
        handleLastPLayerTurn();
        return res;
    }

    private boolean getWinner(Player player) {
        System.out.println("Answer was correct!!!!");
        player.incrementScore();
        System.out.println(player
                + " now has "
                + player.getScore()
                + " Gold Coins.");

        return didPlayerWin();
    }

    public boolean handleWrongAnswer() {
        Player player = players.get(currentPlayer);
        boolean answer;
        player.addFailedAttempts();
        boolean hadSecondChance = player.getFailedAttempts() % 2 == 1;
        if (hadSecondChance) {
            System.out.println("Incorrect answer! " + player + " has a second chance in the same category.");
            askQuestion();
            System.out.print(">> Was the answer correct? [y/n] ");
            boolean correct = readYesNo();
            if (correct) {
                answer = handleCorrectAnswer();
            } else {
                answer = handleWrongAnswer();
            }
        } else {
            System.out.println("Question was incorrectly answered");
            if (player.getSuccessfulAttempts() >= 3) {
                System.out.println("Streak is now over");
                player.setSuccessfulAttempts(0);
            } else {
                System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
                players.get(currentPlayer).setInPenaltyBox(true);
            }
            currentPlayer++;
            handleLastPLayerTurn();
            answer = true;
        }
        player.setFailedAttempts(0);
        return answer;
    }

    public void handleLastPLayerTurn() {
        if (currentPlayer == players.size())
            currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getScore() == 12);
    }
}
