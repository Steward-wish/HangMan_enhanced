package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;

public class hangmanAdditionalTryCatch {
    static void main() {

        Scanner sc = new Scanner(System.in);
        GameUI ui = new GameUI();

        boolean playAgain = true;
        ui.displayWelcome();

        while (playAgain) {
            int players;
            // Select the number of players
            while(true) {
                try {
                    System.out.print("\nPlease enter number of players (minimum 2): ");
                    players = sc.nextInt();
                    System.out.println();

                    while (players < 2) {
                        System.out.print("Invalid. Enter at least 2 players: ");
                        players = sc.nextInt();
                        System.out.println();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid Input!");
                } finally {
                    sc.nextLine(); // clear buffer
                }
            }



            //The first player choose a word, phrase, or sentence.
            System.out.print("Player 1, enter your word / phrase / sentence: ");
            String guessItem = sc.nextLine().toUpperCase();

            //guessItem = guessItem.replaceAll("\\s", "");
            for (int i = 0; i < guessItem.length(); i++) {
                if (!Character.isLetter(guessItem.charAt(0))) {
                    System.out.print("Invalid input. Player 1, enter your word / phrase / sentence: ");
                    guessItem = sc.nextLine().toUpperCase();
                }
            }



            //System.out.println("\n".repeat(30));


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
            String choice;
            while (true) {

                try {
                    choice = sc.next();
                    if (String.valueOf(choice).length() != 1 || !Character.isLetter(choice.charAt(0))) {
                        throw new IllegalArgumentException();
                    } else if (!choice.equals("Y") && !choice.equals("N")) {
                        throw new IllegalArgumentException();
                    }
                    break;
                }catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                    System.out.print("Invalid input. Play again? (Y/N): ");
                } finally {
                    sc.nextLine();// clear buffer
                }

            }

            playAgain = (choice.equals("Y"));
        }
        System.out.println();
        System.out.println("Thank you for your participation!");
        sc.close();
    }
}

