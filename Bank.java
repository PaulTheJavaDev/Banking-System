import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> takenUsers = new HashMap<>(); // Key: username, Value: password
    static Account user; // user is first assigned null, changes in the program later, might not change if the user is not assigned
    static boolean userCreated = false;
    static boolean userIsLoggedIn = false;

    public static void createAccount() {
        System.out.println("What would you like your username to be?");
        String usernameAnswer;

        //loops through the taken Usernames and looks if the selected username is taken
        while (true) {
            usernameAnswer = scanner.nextLine();

            if (takenUsers.containsKey(usernameAnswer)) {
                System.out.println("Unavailable Username, please select another one!");
            } else {
                System.out.printf("\nAvailable username, welcome %s!\n", usernameAnswer);
                break;
            }
        }

        System.out.println("What would you like your password to be?");
        String password = scanner.nextLine();

        //stores the username and password in the HashMap
        takenUsers.put(usernameAnswer, password);
        userCreated = true;
    }

    // Login functionality
    public static void login() {

        Random random = new Random();
        String userID = "user" + random.nextInt(1_000_000, 1_000_000_000) + 1;

        if (userIsLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        // Check if the username and password are correct
        if (takenUsers.containsKey(username) && takenUsers.get(username).equals(password)) {
            userIsLoggedIn = true;
            user = new Account(username, password, userID, 0);
            System.out.println("Login successful! Welcome " + username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    //Logout functionality
    public static void logout() {
        if (!userIsLoggedIn) {
            System.out.println("You are not logged in.");
        } else {
            userIsLoggedIn = false;
            user = null;
            System.out.println("You have logged out successfully.");
        }
    }

    public static void depositMoney() {
        if (!userIsLoggedIn) {
            System.out.println("You must be logged in to perform this action!");
            return;
        }

        System.out.printf("\nYou currently have %d€.\n", user.getBalance());
        System.out.println("How much money would you like to deposit?");
        int depositAmount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (depositAmount > 0) {
            user.depositMoney(depositAmount);
            System.out.printf("Successfully deposited %d€. Your new balance is %d€.\n", depositAmount, user.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a valid number.");
        }
    }

    public static void withdrawMoney() {

        int currentMoney = user.getBalance();

        if (!userIsLoggedIn) {
            System.out.println("You must be logged in to perform this action!");
            return;
        }

        System.out.printf("\nYou currently have %d€.\n", user.getBalance());
        System.out.println("How much money would you like to withdraw?");
        int withdrawAmount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (withdrawAmount > 0) {
            if (withdrawAmount <= user.getBalance()) {
                user.withdrawMoney(withdrawAmount);
                System.out.printf("Successfully withdrew %d€. Your new balance is %d€.\n", withdrawAmount, currentMoney - withdrawAmount);
            } else {
                System.out.println("Insufficient balance. Please enter a smaller amount.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        }
    }
}
