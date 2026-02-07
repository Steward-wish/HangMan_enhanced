package HangMan.SE;

import HangMan.GameLogic;
import HangMan.GameUI;
import HangMan.SE.WordLoader_SE;

public class HangmanSingleplayer_SE {
    public void soloPlayerMode() {
        WordLoader_SE loader = new WordLoader_SE();   // updated
        String wordToGuess = loader.selectRandomWord();

        GameLogic game = new GameLogic(wordToGuess);
        GameUI ui = new GameUI();

        while (!game.isGameOver()) {
            ui.displayGameState(game);
            char guess = ui.getGuessFromUser();
            game.guessLetter(Character.toUpperCase(guess));
        }
        System.out.println("the answer is: "+  wordToGuess);
        ui.displayResult(game);
    }
}