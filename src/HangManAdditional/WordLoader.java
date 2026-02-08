package HangManAdditional;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.Scanner;

// Loads words for Hangman game.

public class WordLoader {
    private static final String[] WORDS = {
            "HAIYAA", "JAVA", "WAHAHA", "HELLO", "HANGMAN"
    };
    private final Random random = new Random();
    private final Scanner sc = new Scanner(System.in);

    public String selectRandomWord() {
        return WORDS[random.nextInt(WORDS.length)];
    }



    // Additional wordLoadLogic

    // player input words
    public String answerWords() {
        //The first player choose a word, phrase, or sentence.
        System.out.print("\nPlayer 1, enter the words (letters and space only): ");
        String guessItem;


        // check input whether is valid
        while (true) {
            guessItem = sc.nextLine().toUpperCase();
            try {
                if (!guessItem.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException();
                }
                break;
            }catch (Exception e) {
                System.out.print("\nInvalid input. Please enter letters and space only: ");
            }
        }



        // clear output    ? unsuccessful
//        System.out.print("\033[H\033[2J");
//        System.out.flush();

        // simple clear screen
        System.out.println("\n".repeat(30));
        return guessItem;
    }

    // generate words on the internet
    public String getRemoteRandomWord() {
        try {

            // 1. creat client
            HttpClient client = HttpClient.newHttpClient();

            // 2. creat request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://random-word-api.herokuapp.com/word?number="+random.nextInt(1,3))).build();
            System.out.println("\nRandom words being obtained from the internet......");

            // 3. send request, get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. process response : ["sulfuric","prediction","pacifist"]
            String result = response.body();
            return result.replace("[", "").replace("]", "").replace("\"", "").replace(","," ").toUpperCase();

        } catch (Exception e) { // use original words when network error
            System.err.println("network connect error!");
            return selectRandomWord();
        }
    }



}