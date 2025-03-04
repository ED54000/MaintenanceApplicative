package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// REFACTOR ME
public class Game implements IGame {
   private ArrayList<String> players = new ArrayList<>();
   private int[] places = new int[6];
   private int[] score = new int[6];
   private boolean[] inPenaltyBox = new boolean[6];

   private Map<String, List<String>> questions = new HashMap<>();

   private int currentPlayer = 0;
   private boolean isGettingOutOfPenaltyBox;

   public Game() {
      questions.put("Pop", new LinkedList<>());
      questions.put("Science", new LinkedList<>());
      questions.put("Sports", new LinkedList<>());
      questions.put("Rock", new LinkedList<>());

      for (String categorie : questions.keySet()) {
         for (int i = 0; i < 50; i++) {
            questions.get(categorie).addLast(categorie + " Question " + i);
         }
      }
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      places[howManyPlayers()] = 1;
      score[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;
      players.add(playerName);

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

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 12)
               places[currentPlayer] = places[currentPlayer] - 12;

            System.out.println(players.get(currentPlayer)
                  + "'s new location is "
                  + places[currentPlayer]);
            System.out.println("The category is " + currentCategory());
            askQuestion();
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 12)
            places[currentPlayer] = places[currentPlayer] - 12;

         System.out.println(players.get(currentPlayer)
               + "'s new location is "
               + places[currentPlayer]);
         System.out.println("The category is " + currentCategory());
         askQuestion();
      }

   }

   private void askQuestion() {
      System.out.println(questions.get(currentCategory()).removeFirst());
   }

   private String currentCategory() {
      return switch (places[currentPlayer] - 1) {
         case 0, 4, 8 -> "Pop";
         case 1, 5, 9 -> "Science";
         case 2, 6, 10 -> "Sports";
         default -> "Rock";
      };
   }

   public boolean handleCorrectAnswer() {
      if (inPenaltyBox[currentPlayer]) {
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
      System.out.println("Answer was corrent!!!!");
      score[currentPlayer]++;
      System.out.println(players.get(currentPlayer)
            + " now has "
            + score[currentPlayer]
            + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == players.size())
         currentPlayer = 0;

      return winner;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size())
         currentPlayer = 0;
      return true;
   }

   private boolean didPlayerWin() {
      return !(score[currentPlayer] == 6);
   }
}
