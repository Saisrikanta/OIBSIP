package Infobyte;


import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int MAX_ATTEMPTS = 5;
        int difficulty = chooseDifficulty(scanner);  

        int round = 1;
        int score = 0;

        while (true) {
            System.out.println("Round " + round);
            int randomNumber = generateNumber(difficulty);  

            int attempts = 0;

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Guess the number (between 1 and " + difficulty + "): ");
                int guess = scanner.nextInt();

                if (guess == randomNumber) {
                    System.out.println("Wah! You have guessed the number correctly");
                    score += calculateScore(attempts, MAX_ATTEMPTS);  
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("The number you entered is too low.");
                } else {
                    System.out.println("The number you entered is too high.");
                }

                attempts++;
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Sorry, Your attempts exceeded. The correct is " + randomNumber);
            }

            System.out.println("Your score after " + round + " rounds is:" + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }

            round++;
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    
    }
    
    private static int chooseDifficulty(Scanner scanner) {
        System.out.println("Choose difficulty (easy , medium , hard ): ");
        String choice = scanner.next();
        switch (choice.toLowerCase()) {
            case "easy":
                return 100;
            case "medium":
                return 500;
            case "hard":
                return 1000;
            default:
                System.out.println("Invalid choice. Defaulting to easy (100).");
                return 100;
        }
    }
    
   
    private static int generateNumber(int difficulty) {
        Random random = new Random();
        return random.nextInt(difficulty) + 1;
    }

    
    private static int calculateScore(int attempts, int maxAttempts) {
        return maxAttempts - attempts;
    }
}
