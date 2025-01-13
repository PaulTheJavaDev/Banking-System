import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    static Scanner scanner = new Scanner(System.in);
    //static HashMap<String, String> takenUsers = new HashMap<>(); //first String = username, second String = password, just a thought tho
    static String[] takenUsernames = {"PaulTheJavaDev"};
    static Account user; //user is first assigned null, changes in the program later, might not change if the user is not assigned
    static boolean userCreated = false;
    static boolean userIsLoggedIn = false;

    public static void createAccount() {
        Random random = new Random();

        System.out.println("What would you like your username to be?");
        String usernameAnswer;

        //loops through the taken Usernames and looks, if the selected username of the user is equal to a taken username
        while (true) {
            usernameAnswer = scanner.nextLine();

            boolean isTaken = false;
            for (String takenUsername : takenUsernames) {
                if (usernameAnswer.equals(takenUsername)) {
                    System.out.println("Unavailable Username, please select another one!");
                    isTaken = true;
                    break;
                }
            }

            if (!isTaken) {
                System.out.printf("\nAvailable username, welcome %s!\n", usernameAnswer);
                break;
            }
        }

        //assigning values to finally create the account
        System.out.println("What would you like your password to be?");
        String password = scanner.nextLine();

        takenUsers.put(usernameAnswer, password);
        addToArray(takenUsernames, user.getUsername());
        userCreated = true;
    }

    //made addToArray private so the Main class can't have access to it
    private static String[] addToArray(String[] array, String newElement) {
        // Create a new array with one additional slot
        String[] newArray = new String[array.length + 1];

        // Copy elements from the original array
        System.arraycopy(array, 0, newArray, 0, array.length);

        newArray[array.length] = newElement;

        return newArray; // Return the updated array
    }


    public static void depositMoney() {
        if (user == null) {
            System.out.println("No account found. Please create an account first.");
            return;
        }

        System.out.printf("\nYou currently have %d€.\n", user.getBalance());
        System.out.println("How much money would you like to deposit?");

        int depositAmount = scanner.nextInt();
        scanner.nextLine();

        if (depositAmount > 0) {
            user.depositMoney(depositAmount);
            System.out.printf("Successfully deposited %d€. Your new balance is %d€.\n", depositAmount, user.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a valid number.");
        }
    }

    public static void withdrawMoney() {
        if (user == null) {
            System.out.println("No account found. Please create an account first.");
            return;
        }

        System.out.printf("\nYou currently have %d€.\n", user.getBalance());
        System.out.println("How much money would you like to withdraw?");

        int withdrawAmount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (withdrawAmount > 0) {
            if (withdrawAmount <= user.getBalance()) {
                user.withdrawMoney(withdrawAmount);
                System.out.printf("Successfully withdrew %d€. Your new balance is %d€.\n", withdrawAmount, user.getBalance() - withdrawAmount);
            } else {
                System.out.println("Insufficient balance. Please enter a smaller amount.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        }
    }
}
