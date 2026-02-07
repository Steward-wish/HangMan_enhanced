package HangMan;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
        System.out.print("\nPlayer 1, enter your word / phrase / sentence: ");
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

    public String getRemoteRandomWord() {
        try {
            // 1. creat client
            HttpClient client = HttpClient.newHttpClient();

            // 2. creat request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://random-word-api.herokuapp.com/word?number="+random.nextInt(1,3))).build();

            // 3. send request, get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.err.println("Random words are being obtained from the internet......");

            // 4. process response : ["sulfuric","prediction","pacifist"]
            String result = response.body();
            return result.replace("[", "").replace("]", "").replace("\"", "").replace(","," ").toUpperCase();

        } catch (Exception e) {
            System.err.println("network connect error!");
            return selectRandomWord(); // use original words
        }
    }



}