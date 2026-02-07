package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;


public class HangmanAdditionalMulti {
     public void multiPlayerMode() {

        Scanner sc = new Scanner(System.in);
        GameUI ui = new GameUI();
        WordLoader loader = new WordLoader();
        int players, choise;
         String guessItem;

         while (true) {
             try {
                 System.out.println("\nChoose enter mode\n1. Self-enter\n2. Random word");
                 System.out.print("Enter: ");
                 choise = sc.nextInt();
                 if (choise < 0 || choise > 2) {
                     throw new IllegalArgumentException();
                 }
                 break;
             } catch (Exception e) {
                 System.out.println("Invalid input, enter a number between 0-2.");
             } finally {
                 sc.nextLine();
             }
         }


         if (choise == 1) {
             //The first player choose a word, phrase, or sentence.
             guessItem = loader.answerWords();
         } else {

             guessItem = loader.getRemoteRandomWord();
         }






        // input players
         players = ui.getPlayers();
         GameLogic[] game = new GameLogic[players];
         for (int i = 0; i < players; i++) {
             game[i] = new GameLogic(guessItem);
         }



         // Main multiplayer loop
         while (!game[0].isGameOver()) {

             for (int i = 0; i < players; i++) {

                 // skip lost player
                 if (game[i].getIncorrectGuesses()>=6) {
                     continue;
                 }
                 // skip first player when player1 input answerWords
                 if (choise == 1) {
                     if (i == 0) continue;
                 }

                 ui.displayAdditionalGameState(game[0], game[i]); // show current state
                 //ui.displayPlayersTurn(game[0]);  // show current player's turn

                 System.out.printf("Player %d's turn\n", i+1);
                 System.out.printf("player %d's incorrect numbers: %d%n", i+1, game[i].getIncorrectGuesses());
                 char guess = ui.getGuessFromUser();// get user input

                 if (game[i].guessLetter((Character.toUpperCase(guess)))) {
                     System.out.printf("Letter %s is correct!\n", guess);
                 }else {
                     System.out.printf("Letter %s is wrong!\n", guess);
                 }
                 game[0].guessLetter((Character.toUpperCase(guess)));
                 // Display final result
                 if (game[0].isGameOver()) {
                     if (game[0].isWon()) {
                         ui.displayAdditionalResult(game[0], i);
                         break;
                     } else if (game[i].getIncorrectGuesses()>=6) {
                         System.out.printf("player %d, sorry, you LOST!\n", i+1);

                     }
                 }

             }

         }
         // result of the game if all players lost
         if (game[0].isLost()) {
             ui.displayResult(game[0]);
         }



        System.out.println();
        System.out.println("Thank you for your participation!");
    }
}
