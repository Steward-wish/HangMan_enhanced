package Other_HangMan.SE;

import HangMan.GameLogic;
import HangMan.WordLoader;

import java.util.*;

public class HangmanMultiplayer_CE {
    private int[] scores;   // scores persist across rounds

    public void multiPlayerMode() {
        Scanner sc = new Scanner(System.in);
        GameUI_SE ui = new GameUI_SE();
        WordLoader loader = new WordLoader();

        int players = ui.getPlayers();
        scores = new int[players]; // initialize once

        boolean playAgain;
        int wordSetterIndex = 0; // start with player 1

        do {
            // Word setter chooses word
            System.out.printf("Player %d, please set the word/phrase: ", wordSetterIndex + 1);
            String guessItem = loader.answerWords();

            // Initialize game state
            GameLogic[] game = new GameLogic[players];
            for (int i = 0; i < players; i++) {
                game[i] = new GameLogic(guessItem);
            }

            // Rotation order (no shuffle, just sequential)
            List<Integer> order = new ArrayList<>();
            for (int i = 0; i < players; i++) {
                if (i != wordSetterIndex) {
                    order.add(i);
                }
            }

            boolean roundOver = false;

            // Main game loop
            while (!roundOver) {
                for (int idx : order) {
                    if (game[idx].isLost()) {
                        System.out.printf("Player %d has already LOST and is skipped.\n", idx + 1);
                        continue;
                    }

                    ui.displayAdditionalGameState(game[wordSetterIndex], game[idx]);
                    System.out.printf("Player %d's turn\n", idx + 1);

                    char guess = ui.getGuessFromUser();

                    if (game[idx].guessLetter(Character.toUpperCase(guess))) {
                        System.out.printf("Letter %s is correct!\n", guess);
                    } else {
                        System.out.printf("Letter %s is wrong!\n", guess);
                    }

                    game[wordSetterIndex].guessLetter(Character.toUpperCase(guess));

                    // If word is solved → round ends
                    if (game[wordSetterIndex].isWon()) {
                        roundOver = true;
                        break;
                    }
                }

                // End round if all guessers lost
                boolean allLost = true;
                for (int idx : order) {
                    if (!game[idx].isLost()) {
                        allLost = false;
                        break;
                    }
                }
                if (allLost) {
                    roundOver = true;
                }
            }

            // Award points after round ends
            List<Integer> alive = new ArrayList<>();
            List<Integer> lost = new ArrayList<>();

            for (int idx : order) {
                if (game[idx].isLost()) {
                    lost.add(idx);
                } else {
                    alive.add(idx);
                }
            }

            boolean solved = game[wordSetterIndex].isWon();

            if (solved) {
                // Case 1: someone solved → all alive get 1 point, setter gets points for each loser
                for (int idx : alive) {
                    scores[idx]++;
                    System.out.printf("Player %d is alive and earns 1 point!\n", idx + 1);
                }
                scores[wordSetterIndex] += lost.size();
                if (!lost.isEmpty()) {
                    System.out.printf("Player %d gains %d points from lost players.\n", wordSetterIndex + 1, lost.size());
                }
            } else if (alive.isEmpty()) {
                // Case 2: all lost → setter gets all points
                scores[wordSetterIndex] += lost.size();
                System.out.printf("All guessers lost! Player %d gains %d points.\n", wordSetterIndex + 1, lost.size());
            } else {
                // Case 3: some lost, some alive (no one solved)
                for (int idx : alive) {
                    scores[idx]++;
                    System.out.printf("Player %d is alive and earns 1 point!\n", idx + 1);
                }
                scores[wordSetterIndex] += lost.size();
                if (!lost.isEmpty()) {
                    System.out.printf("Player %d gains %d points from lost players.\n", wordSetterIndex + 1, lost.size());
                }
            }

            // Replay option
            playAgain = ui.playAgain();
            if (playAgain) {
                wordSetterIndex = (wordSetterIndex + 1) % players; // rotate word setter
            }

        } while (playAgain);

        // Final scoreboard (accumulated scores)
        System.out.println("\n===== Final Scores =====");
        System.out.printf("%-10s %-10s\n", "Player", "Score");
        for (int i = 0; i < players; i++) {
            System.out.printf("%-10d %-10d\n", i + 1, scores[i]);
        }
    }
}