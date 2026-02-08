package HangMan;
import java.util.ArrayList;
import java.util.Scanner;



public class GameUI {
    private final Scanner scanner = new Scanner(System.in);



    public void displayWelcome() {
        System.out.println("===== Welcome to Hangman game! =====");
    }

    public void displayGameState(GameLogic game) {
        System.out.println();
        System.out.println("------------------------------------");
        drawHangman(game.getIncorrectGuesses());
        System.out.println("Word: " + game.getDisplayWord());
        System.out.println("Incorrect Guesses: " + game.getIncorrectGuesses() + "/" + game.getMaxIncorrectGuesses());
        System.out.println("Used Letters: " + formatGuessedLetters(game.getGuessedLetters()));
        System.out.println("------------------------------------");
    }

    public char getGuessFromUser() {
        System.out.print("Guess a letter: ");
        String input = scanner.nextLine().toUpperCase();


        while (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
            System.out.print("In valid input. Guess a letter: ");
            input = scanner.nextLine().toUpperCase();
        }
        return input.charAt(0);
    }

    public void displayResult(GameLogic game) {
        System.out.println();
        System.out.println("===== Game Over! =====");
        if (game.isWon()) {

            System.out.println("Congratulations, you have WON!");
            System.out.println();
        } else {
            System.out.print("Sorry, you LOST! ");
            System.out.println("The word was: " + game.getHiddenWord());
            drawHangman(game.getIncorrectGuesses());
        }
    }

    private String formatGuessedLetters(ArrayList<Character> letters) {
        if (letters.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < letters.size(); i++) {
            sb.append(letters.get(i));
            if (i < letters.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private void drawHangman(int stage) {
        String[] hangmanStages = {
                "+---+\n|   |\n|\n|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  |\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|  /\n|\n=======\n",
                "+---+\n|   |\n|   O\n|  /|\\\n|  / \\\n|\n=======\n"
        };
        System.out.print(hangmanStages[stage]);
    }


    // Additional UiLogic

    // show the enhanceGame's statue
    public void displayAdditionalGameState(GameLogic game1, GameLogic game2) {
        System.out.println();
        System.out.println("------------------------------------");
        game1.setIncorrectGuesses(game2.getIncorrectGuesses());
        drawHangman(game1.getIncorrectGuesses());
        System.out.println("Word: " + game1.getDisplayWord());
        System.out.println("Used Letters: " + formatGuessedLetters(game1.getGuessedLetters()));
        System.out.println("------------------------------------");
    }

    // set the number of players
    public int getPlayers() {

        // check the input whether is valid
        while(true) {
            try {
                System.out.print("\nPlease enter number of players (minimum 2, suggest less than 4 players): ");
                //Additional variables
                int players = scanner.nextInt();
                System.out.println();

                while (players < 2) {
                    System.out.print("Invalid. Enter at least 2 players: ");
                    players = scanner.nextInt();
                    System.out.println();
                }
                return players;
            } catch (Exception e) {
                System.out.println("Invalid Input!");
            } finally {
                scanner.nextLine(); // clear buffer
            }
        }
    }

    // show the result of enhanceGame
    public void displayAdditionalResult(GameLogic game, int i) {
        System.out.println();
        System.out.println("===== Game Over! =====");
        if (game.isWon()) {
            System.out.printf("Congratulations, player %d, you have WON!\n", i);
            System.out.println();
        } else {
            System.out.print("Sorry, all you LOST!");
            System.out.println();
            System.out.println("The word was: " + game.getHiddenWord());
            drawHangman(game.getIncorrectGuesses());
        }
    }

    // Replay option
    public boolean playAgain() {
        System.out.print("Do you want to play again? (Y/N): ");
        String choice;

        // check the input whether is valid
        while (true) {
            try {
                choice = scanner.next().toUpperCase();
                if (choice.length() != 1 || !Character.isLetter(choice.charAt(0))) {
                    throw new IllegalArgumentException();
                } else if (!choice.equals("Y") && !choice.equals("N")) {
                    throw new IllegalArgumentException();
                }
                break;
            }catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
                System.out.print("Invalid input. Play again? (Y/N): ");
            } finally {
                scanner.nextLine();// clear buffer
            }

        }
        return choice.equals("Y");
    }






}