package HangMan.SE;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class WordLoader_SE {
    private final Random random = new Random();
    private final Scanner sc = new Scanner(System.in);
    private List<String> words = new ArrayList<>();

    // Load words from the API
    public WordLoader_SE() {
        try {
            // Connect to the API
            URL url = new URL("https://random-word-api.herokuapp.com/word?number=5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read response
            InputStream in = conn.getInputStream();
            Scanner apiScanner = new Scanner(in);

            StringBuilder response = new StringBuilder();
            while (apiScanner.hasNext()) {
                response.append(apiScanner.nextLine());
            }
            apiScanner.close();

            // Response looks like: ["apple","banana","cat"]
            String raw = response.toString();
            raw = raw.replace("[", "").replace("]", "").replace("\"", "");
            String[] wordArray = raw.split(",");

            for (String w : wordArray) {
                words.add(w.trim().toUpperCase());
            }

            // fallback if empty
            if (words.isEmpty()) {
                words = Arrays.asList("JAVA", "HANGMAN", "DEFAULT");
            }

        } catch (Exception e) {
            System.out.println("Could not fetch from API, using fallback list.");
            words = Arrays.asList("JAVA", "HANGMAN", "DEFAULT");
        }
    }

    // Pick random word
    public String selectRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    // Word setter chooses custom word
    public String answerWords() {
        String guessItem = sc.nextLine().toUpperCase();
        System.out.print("\n".repeat(30)); // clear screen

        while (!guessItem.matches("[A-Z ]+")) {
            System.out.print("Invalid input. Please enter letters only: ");
            guessItem = sc.nextLine().toUpperCase();
            System.out.print("\n".repeat(30));
        }
        return guessItem;
    }
}