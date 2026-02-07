package HangMan;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        System.out.print("\033[H\033[2J");
        System.out.flush();


        //guessItem = guessItem.replaceAll("\\s", "");
        for (int i = 0; i < guessItem.length(); i++) {
            if (!Character.isLetter(guessItem.charAt(0))) {
                System.out.print("Invalid input. Player 1, enter your word / phrase / sentence: ");
                guessItem = sc.nextLine().toUpperCase();
                System.out.print("\033[H\033[2J");
                System.out.flush();

            }
        }
        return guessItem;
    }



    public static String getRandomWord() {
        try {
            // 1. 将所有行读取到 List 中
            List<String> words = Files.readAllLines(Paths.get("words.txt"));

            // 2. 检查文件是否为空
            if (words.isEmpty()) return "DEFAULT";

            // 3. 随机选择一行
            Random rand = new Random();
            return words.get(rand.nextInt(words.size())).toUpperCase();

        } catch (IOException e) {
            System.err.println("错误：无法读取单词文件！");
            return "ERROR";
        }
    }

}