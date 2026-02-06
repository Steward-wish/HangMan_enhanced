package HangMan;
import java.util.Scanner;

public class HangmanAdditional {
    private static int players;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        GameUI ui = new GameUI(); // user interface
        ui.displayWelcome(); // show welcome
        do {
            setPlayers();
            GameLogic game = new GameLogic(setWords()); // rules engine and load words
            // Purpose: Main game loop controlling play until game ends.
            while (!game.isGameOver()) {
                ui.displayGameState(game); // show current state
                char guess = ui.getGuessFromUser(); // get user input
                game.guessLetter(Character.toUpperCase(guess));
            }
            ui.displayResult(game); // show final result
        }while (!isQuit());

    }




    // ----- Select whether quit -----
    public static boolean isQuit() {
        System.out.print("quit (y/n): ");

        while(true) {
            String isQuit = sc.next();
            if (isQuit.equals("y") || isQuit.equals("n")) {
                return isQuit.equals("y");
            }else {
                throw new IllegalArgumentException("Invalid enter! enter again: ");
            }
        }
    }


    // ----- Select number of players -----
    public static void setPlayers() {
        System.out.println();

        try {
            System.out.print("Enter number of players (minimum 2): ");
            players = sc.nextInt();
            sc.nextLine(); // clear buffer
            if (players < 2) {
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException e){
            System.out.println("players cannot be leas than 2.");

        }finally {
            sc.nextLine();
        }

    }

    // ----- enter the word/phrase/sentence -----
    public static String setWords() {
        System.out.print("Player 1, enter a word / phrase / sentence: ");
        return sc.nextLine().toUpperCase();
    }

}
