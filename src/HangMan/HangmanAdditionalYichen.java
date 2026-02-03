package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;

public class HangmanAdditionalYichen {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GameUI ui = new GameUI();

        boolean playAgain = true;
        ui.displayWelcome();

        while (playAgain) {

            // Select the number of players
//            try {
                System.out.print("Please enter number of players (minimum 2): ");
                int players = sc.nextInt();
                sc.nextLine();
                System.out.println();
//            }
//            catch (InputMismatchException e) {
//                throw new IllegalArgumentException("Invalid Input!");
//            }

            while (players < 2) {
                System.out.print("Invalid. Enter at least 2 players: ");
                players = sc.nextInt();
                sc.nextLine();
                System.out.println();
            }

            //The first player choose a word, phrase, or sentence.
            System.out.print("Player 1, enter your word / phrase / sentence: ");
            String guessItem = sc.nextLine().toUpperCase();
            //guessItem = guessItem.replaceAll("\\s", "");


            //
            System.out.println("\n".repeat(30));

            GameLogic game = new GameLogic(guessItem);

            int currentPlayer = 2;

            // Main game loop
            while (!game.isGameOver()) {

                ui.displayGameState(game);

                System.out.println("Player " + currentPlayer + "'s turn");
                char guess = ui.getGuessFromUser();

                game.guessLetter((Character.toUpperCase(guess)));

                currentPlayer++;
                if (currentPlayer > players) {
                    currentPlayer = 2;
                }
            }

            // Display final result
            ui.displayResult(game);


            // Replay option
            System.out.print("Do you want to play again? (Y/N): ");
            char choice = sc.nextLine().toUpperCase().charAt(0);

            playAgain = (choice == 'Y');
        }
        System.out.println();
        System.out.println("Thank you for your participation!");
        sc.close();
    }
}
