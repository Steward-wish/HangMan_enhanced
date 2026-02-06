package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;

public class HangmanAdditionalMain {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GameUI ui = new GameUI();
        boolean playAgain;
        int choise;

        do {
            ui.displayWelcome();
            System.out.println("\n+---+\n|   |\n|   O\n|  /|\\\n|  / \\\n|\n=======\n");
            while (true) {
                try {
                    System.out.println("\nChoose game mode\n1. Solo player\n2. Multiplayer\n0. exit");
                    System.out.print("Enter: ");
                    choise = sc.nextInt();
                    if (choise < 0 || choise > 2) {
                        throw new IllegalArgumentException();
                    }
                    break;
                }catch (Exception e) {
                    System.out.println("Invalid input, enter a number between 0-2.");
                }finally {
                    sc.nextLine();
                }
            }

            if (choise == 1) {
                HangmanBasic solo = new HangmanBasic();
                solo.soloPlayerMode();
            } else if (choise == 2) {
                HangmanAdditionalMulti mutiPlayer = new HangmanAdditionalMulti();
                mutiPlayer.multiPlayerMode();

            } else {
                System.out.println("thank you");
            }
            playAgain = ui.playAgain();
        }while (playAgain);


    }


}

/*while (playAgain) {
        // Select the number of players
        System.out.print("Please enter number of players (minimum 2): ");
int players = sc.nextInt();
                sc.nextLine();
                System.out.println();
                while (players < 2) {
        System.out.print("Invalid. Enter at least 2 players: ");
players = sc.nextInt();
                    sc.nextLine();
                    System.out.println();
                }
                        //The first player choose a word, phrase, or sentence.
                        System.out.print("Player 1, enter your word / phrase / sentence: ");
String guessItem = sc.nextLine().toUpperCase();
                System.out.print("\033[H\033[2J");
                System.out.flush();
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
            sc.close();*/
