package HangManAdditional;

//import java.util.InputMismatchException;
import java.util.Scanner;


public class HangmanAdditionalMulti {
     public void multiPlayerMode() {

        Scanner sc = new Scanner(System.in);
        GameUI ui = new GameUI();
        WordLoader loader = new WordLoader();
        int players, choise;
         String guessItem;

         // check the choice whether is valid
         while (true) {
             try {
                 System.out.println("\nChoose enter mode\n1. Self-enter\n2. Random word");
                 System.out.print("Enter: ");
                 choise = sc.nextInt();
                 if (choise < 1 || choise > 2) {
                     throw new IllegalArgumentException();
                 }
                 break;
             } catch (Exception e) {
                 System.out.println("Invalid input, enter a number between 1-2.");
             } finally {
                 sc.nextLine();
             }
         }


         if (choise == 1) {
             //The first player choose a word, phrase, or sentence.
             guessItem = loader.answerWords();
         } else {
             // words are generated on the internet
             guessItem = loader.getRemoteRandomWord();
         }






        // input players
         players = ui.getPlayers();
         GameLogic[] game = new GameLogic[players];
         for (int i = 0; i < players; i++) {
             game[i] = new GameLogic(guessItem);
         }



         // Main multiplayer loop
         while (!game[0].isGameOver()) { // use first game object control game statue

             for (int i = 1; i <= players; i++) {

                 // skip lost players
                 if (game[i-1].getIncorrectGuesses()>=6) {
                     continue;
                 }
                 // skip first player when first player input answerWords
                 if (choise == 1) {
                     if (i == 1) continue;
                 }

                 ui.displayAdditionalGameState(game[0], game[i-1]); // show current game's state
                 //ui.displayPlayersTurn(game[0]);

                 System.out.printf("Player %d's turn\n", i); // show current player's turn
                 System.out.printf("player %d's incorrect numbers: %d%n", i, game[i-1].getIncorrectGuesses());// show current player's incorrectGuess
                 char guess = ui.getGuessFromUser();// get user input

                 // check and show the current letter whether is correct
                 if (game[i-1].guessLetter((Character.toUpperCase(guess)))) {
                     System.out.printf("Letter %s is correct!\n", guess);
                 }else {
                     System.out.printf("Letter %s is wrong!\n", guess);
                 }

                 game[0].guessLetter((Character.toUpperCase(guess))); // give this object the current player's guessResult

                 // Display final result
                 if (game[0].isGameOver()) {
                     if (game[0].isWon()) { // if someone win, exit
                         ui.displayAdditionalResult(game[0], i);
                         break;
                     } else if (game[i-1].getIncorrectGuesses()>=6) { // if someone lost, others continue
                         System.out.printf("player %d, sorry, you LOST!\n", i);
                     }
                 }

             }

         }
         // result of the game if all players lost
         if (game[1].isLost()) {
             ui.displayResult(game[1]);
         }



        System.out.println();
        System.out.println("Thank you for your participation!");
    }
}
