package trivia;

import java.util.*;

enum Categories {
    POP("Pop"),
    SCIENCE("Science"),
    SPORT("Sports"),
    ROCK("Rock"),
    ;

    private final String stringValue;

    Categories(final String categorie) {
        stringValue = categorie;
    }

    public String toString() {
        return stringValue;
    }
}

public class Game implements IGame {

    private final ArrayList<Player> players = new ArrayList<>();
    private final Map<Categories, LinkedList<String>> questions = new EnumMap<>(Categories.class);

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (Categories category : Categories.values()) {
            LinkedList<String> categoryQuestions = new LinkedList<>();
            for (int i = 0; i < 50; i++) {
                categoryQuestions.add(category + " Question " + i);
            }
            questions.put(category, categoryQuestions);
        }
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean addPlayer(String playerName) {
        players.add(new Player(playerName));
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (players.get(currentPlayer).getInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
                jouer(roll);
            } else {
                System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {
            jouer(roll);
        }
    }

    private void jouer(int roll) {
        players.get(currentPlayer).setPlayerPosition(players.get(currentPlayer).getPlayerPosition() + roll);
        if (players.get(currentPlayer).getPlayerPosition() > 12)
            players.get(currentPlayer).setPlayerPosition(players.get(currentPlayer).getPlayerPosition() - 12);
        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + players.get(currentPlayer).getPlayerPosition());
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        System.out.println(questions.get(currentCategory()).removeFirst());
    }

    private Categories currentCategory() {
        int position = players.get(currentPlayer).getPlayerPosition() - 1;
        int nbCat = Categories.values().length;
        int categoryIndex = position % nbCat;
        return Categories.values()[categoryIndex];
    }

    public boolean handleCorrectAnswer() {
        boolean res;
        if (players.get(currentPlayer).getInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                res = getWinner();
            } else {
                res = true;
            }
        } else {
            res = getWinner();
        }
        currentPlayer++;
        handlLastPLayerTurn();
        return res;
    }

    private boolean getWinner() {
        System.out.println("Answer was correct!!!!");
        players.get(currentPlayer).setScore(players.get(currentPlayer).getScore() + 1);
        System.out.println(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).getScore()
                + " Gold Coins.");

        return didPlayerWin();
    }

    public boolean handleWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        handlLastPLayerTurn();
        return true;
    }

    public void handlLastPLayerTurn() {
        if (currentPlayer == players.size())
            currentPlayer = 0;
    }

    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getScore() == 6);
    }
}
