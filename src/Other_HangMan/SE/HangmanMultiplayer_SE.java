package Other_HangMan.SE;

import HangMan.GameLogic;
import HangMan.WordLoader;

import java.util.*;

public class HangmanMultiplayer_SE {
    private String[] playerNames;
    private int[] scores;   // scores persist across rounds

    public void multiPlayerMode() {
        Scanner sc = new Scanner(System.in);
        GameUI_SE ui = new GameUI_SE();
        WordLoader loader = new WordLoader();

        int players = ui.getPlayers();
        playerNames = new String[players];
        scores = new int[players]; // initialize once

        // Input player names
        for (int i = 0; i < players; i++) {
            System.out.printf("Player %d, enter your name: ", i + 1);
            playerNames[i] = sc.next();
        }

        boolean playAgain;
        int wordSetterIndex = 0; // start with player 1

        do {
            // Word setter chooses word
            System.out.printf("%s, please set the word/phrase: ", playerNames[wordSetterIndex]);
            String guessItem = loader.answerWords();

            // Initialize game state
            GameLogic[] game = new GameLogic[playerNames.length];
            for (int i = 0; i < playerNames.length; i++) {
                game[i] = new GameLogic(guessItem);
            }

            // Build random but static rotation order for this round
            List<Integer> order = new ArrayList<>();
            for (int i = 0; i < playerNames.length; i++) {
                if (i != wordSetterIndex) {
                    order.add(i);
                }
            }
            Collections.shuffle(order); // randomize once per round

            boolean roundOver = false;

            // Main game loop
            while (!roundOver) {
                for (int idx : order) {
                    if (game[idx].isLost()) {
                        System.out.printf("%s has already LOST and is skipped.\n", playerNames[idx]);
                        continue;
                    }

                    ui.displayAdditionalGameState(game[wordSetterIndex], game[idx]);
                    System.out.printf("%s's turn\n", playerNames[idx]);

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
                    System.out.printf("%s is alive and earns 1 point!\n", playerNames[idx]);
                }
                scores[wordSetterIndex] += lost.size();
                if (!lost.isEmpty()) {
                    System.out.printf("%s gains %d points from lost players.\n", playerNames[wordSetterIndex], lost.size());
                }
            } else if (alive.isEmpty()) {
                // Case 2: all lost → setter gets all points
                scores[wordSetterIndex] += lost.size();
                System.out.printf("All guessers lost! %s gains %d points.\n", playerNames[wordSetterIndex], lost.size());
            } else {
                // Case 3: some lost, some alive (no one solved)
                for (int idx : alive) {
                    scores[idx]++;
                    System.out.printf("%s is alive and earns 1 point!\n", playerNames[idx]);
                }
                scores[wordSetterIndex] += lost.size();
                if (!lost.isEmpty()) {
                    System.out.printf("%s gains %d points from lost players.\n", playerNames[wordSetterIndex], lost.size());
                }
            }

            // Replay option
            playAgain = ui.playAgain();
            if (playAgain) {
                wordSetterIndex = (wordSetterIndex + 1) % playerNames.length; // rotate word setter
            }

        } while (playAgain);

        // Final scoreboard (accumulated scores)
        System.out.println("\n===== Final Scores =====");
        System.out.printf("%-10s %-15s %-5s\n", "Player", "Name", "Score");
        for (int i = 0; i < playerNames.length; i++) {
            System.out.printf("%-10d %-15s %-5d\n", i + 1, playerNames[i], scores[i]);
        }
    }
}