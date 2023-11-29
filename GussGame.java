import java.util.Random;
import java.util.Scanner;

class GuessGame {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scr = new Scanner(System.in);

        System.out.println("***************************************");
        System.out.println("Welcome to the Number Guessing Game..");
        System.out.println("***************************************");

        int randomNumber = random.nextInt(100) + 1;
        System.out.println("Random number is " + randomNumber);

        int tryCount = 0;
        while (true) {
            System.out.println("Enter your guess (1-100):");

            int playerGuess = scr.nextInt();
            tryCount++;

            if (playerGuess == randomNumber) {
                System.out.println("Congratulations Your Guess is Correct");
                System.out.println("It took you " + tryCount + " tries");
                break;
            } else if (randomNumber > playerGuess) {
                System.out.println("Your Guess is too Low. Try again.");
            } else {
                System.out.println("Your Guess is Too High. Try again.");
            }
        }

        scr.close();

    }
}