package HangMan;

import java.util.ArrayList;

// Handles the rules and state of the Hangman game.
public class GameLogic_SE {
    private static final int MAX_INCORRECT_GUESSES = 6;
    private final String hiddenWord;          // the word to guess
    private String displayWord;               // underscores + revealed letters
    private final ArrayList<Character> guessedLetters; // track guesses
    private int incorrectGuesses;             // count wrong guesses

    public GameLogic_SE(String word) {
        this.hiddenWord = word;
        this.displayWord = "_".repeat(word.length());

        // preserve spaces in displayWord
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (word.charAt(i) == ' ') {
                this.displayWord = displayWord.substring(0, i) + " " + displayWord.substring(i + 1);
            }
        }

        this.guessedLetters = new ArrayList<>();
        this.incorrectGuesses = 0;
    }

    // Process a guessed letter. Returns true if correct, false if incorrect.
    public boolean guessLetter(char guess) {
        if (guessedLetters.contains(guess)) {
            return false;
        }
        guessedLetters.add(guess);

        boolean correct = false;
        StringBuilder newDisplay = new StringBuilder(displayWord);

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == guess) {
                newDisplay.setCharAt(i, guess);
                correct = true;
            }
        }

        displayWord = newDisplay.toString();

        if (!correct) {
            incorrectGuesses++;
        }
        return correct;
    }

    public String getDisplayWord() { return displayWord; }
    public String getHiddenWord() { return hiddenWord; }
    public ArrayList<Character> getGuessedLetters() { return guessedLetters; }
    public int getIncorrectGuesses() { return incorrectGuesses; }
    public int getMaxIncorrectGuesses() { return MAX_INCORRECT_GUESSES; }
    public boolean isGameOver() { return isWon() || isLost(); }
    public boolean isWon() { return displayWord.equals(hiddenWord); }
    public boolean isLost() { return incorrectGuesses >= MAX_INCORRECT_GUESSES; }
    public void setIncorrectGuesses(int incorrectGuesses) { this.incorrectGuesses = incorrectGuesses; }
}