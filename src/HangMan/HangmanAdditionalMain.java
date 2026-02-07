package HangMan;

//import java.util.InputMismatchException;
import java.util.Scanner;

public class HangmanAdditionalMain {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GameUI ui = new GameUI();
        boolean playAgain;
        int choise;

        do {
            ui.displayWelcome();
            System.out.println("\n+---+\n|   |\n|   O\n|  /|\\\n|  / \\\n|\n=======\n");
            while (true) {
                try {
                    System.out.println("\nChoose game mode\n1. Solo player\n2. Multiplayer\n0. exit");
                    System.out.print("Enter: ");
                    choise = sc.nextInt();
                    if (choise < 0 || choise > 2) {
                        throw new IllegalArgumentException();
                    }
                    break;
                }catch (Exception e) {
                    System.out.println("Invalid input, enter a number between 0-2.");
                }finally {
                    sc.nextLine();
                }
            }

            if (choise == 1) {
                HangmanBasic solo = new HangmanBasic();
                solo.soloPlayerMode();
            } else if (choise == 2) {
                HangmanAdditionalMulti multiPlayer = new HangmanAdditionalMulti();
                multiPlayer.multiPlayerMode();

            } else {
                System.out.println("thank you");
            }
            playAgain = ui.playAgain();
        }while (playAgain);


    }


}

