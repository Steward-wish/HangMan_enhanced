package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;


public class HangmanAdditionalMulti {
     public void multiPlayerMode() {

        Scanner sc = new Scanner(System.in);
        GameUI ui = new GameUI();
        WordLoader loader = new WordLoader();
        int players;


        // input players
         players = ui.getPlayers();
         //The first player choose a word, phrase, or sentence.
         String guessItem = loader.answerWords();
         GameLogic[] game = new GameLogic[players];
         for (int i = 0; i < players; i++) {
             game[i] = new GameLogic(guessItem);
         }

         // Main game loop
         while (!game[0].isGameOver()) {

             for (int i = 1; i < players; i++) {

                 // skip lost player
                 if (game[i].getIncorrectGuesses()>=6) {
                     continue;
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
                         ui.displayAdditionalResult(game[0], i+1);
                         break;
                     } else if (game[i].isLost()) {
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
//steward
    }
}
