package trivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// REFACTOR ME
public class Game implements IGame {
    private ArrayList<Player> players = new ArrayList<>();
    private Map<String, LinkedList<String>> questions = new HashMap<>();
    private final List<String> categories = Arrays.asList("Pop", "Science", "Sports", "Rock");

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (String categorie : categories) {
            questions.put(categorie, new LinkedList<>());
            for (int i = 0; i < 50; i++) {
                questions.get(categorie).addLast(categorie + " Question " + i);
            }
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

    private String currentCategory() {
        return switch (players.get(currentPlayer).getPlayerPosition() - 1) {
            case 0, 4, 8 -> "Pop";
            case 1, 5, 9 -> "Science";
            case 2, 6, 10 -> "Sports";
            default -> "Rock";
        };
    }

    public boolean handleCorrectAnswer() {
        if (players.get(currentPlayer).getInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                return getWinner();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size())
                    currentPlayer = 0;
                return true;
            }
        } else {
            return getWinner();
        }
    }

    private boolean getWinner() {
        System.out.println("Answer was correct!!!!");
        players.get(currentPlayer).setScore(players.get(currentPlayer).getScore() + 1);
        System.out.println(players.get(currentPlayer)
                + " now has "
                + players.get(currentPlayer).getScore()
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size())
            currentPlayer = 0;

        return winner;
    }

    public boolean handleWrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).setInPenaltyBox(true);

        currentPlayer++;
        if (currentPlayer == players.size())
            currentPlayer = 0;
        return true;
    }

    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).getScore() == 6);
    }
}
