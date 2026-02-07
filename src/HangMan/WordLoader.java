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

    // Word setter chooses custom word
    public String answerWords() {
        String guessItem = sc.nextLine().toUpperCase();
        System.out.print("\n".repeat(30)); // clear screen

        // Validate input: must start with a letter
        while (guessItem.isEmpty() || !Character.isLetter(guessItem.charAt(0))) {
            System.out.print("Invalid input. Please enter a word/phrase: ");
            guessItem = sc.nextLine().toUpperCase();
            System.out.print("\n".repeat(30));
        }
        return guessItem;
    }
}