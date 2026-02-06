package HangMan;
import java.util.Random;
import java.util.Scanner;

// Loads words for Hangman game.

public class WordLoader {
    private static final String[] WORDS = {
            "HAIYAA", "JAVA", "WAHAHA", "WALLAWE", "HANGMAN"
    };
    private final Random random = new Random();
    private final Scanner sc = new Scanner(System.in);

    public String selectRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }

    // Additional wordLoadLogic
    public String answerWords() {
        //The first player choose a word, phrase, or sentence.
        System.out.print("Player 1, enter your word / phrase / sentence: ");
        String guessItem = sc.nextLine().toUpperCase();
        System.out.print("\n".repeat(30));



        //guessItem = guessItem.replaceAll("\\s", "");
        for (int i = 0; i < guessItem.length(); i++) {
            if (!Character.isLetter(guessItem.charAt(0))) {
                System.out.print("Invalid input. Player 1, enter your word / phrase / sentence: ");
                guessItem = sc.nextLine().toUpperCase();

                System.out.print("\n".repeat(30));
                //System.out.print("\033[H\033[2J");
                //System.out.flush();

            }
        }
        return guessItem;
    }


}