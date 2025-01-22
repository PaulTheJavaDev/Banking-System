import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {

    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, String> takenUsers = new HashMap<>(); // Key: username, Value: password
    static Account user; // user is first assigned null, changes in the program later, might not change if the user is not assigned
    static boolean userCreated = false;
    static boolean userIsLoggedIn = false;
    static String userID = "user" + new Random().nextInt(1_000_000, 1_000_000_000) + 1;

    public void createAccount() {
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

        //stores the username and password in the HashMap + creates an account (helpful for transactions between existing Accounts)
        takenUsers.put(usernameAnswer, password);
        new Account(usernameAnswer, password, userID, 0);
        userCreated = true;
    }

    public void depositMoney() {
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

    public void withdrawMoney() {

        int currentMoney = user.getBalance();

        if (!userIsLoggedIn) {
            System.out.println("You must be logged in to perform this action!");
            return;
        }

        System.out.printf("\nYou currently have %d€.\n", currentMoney);
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
            System.out.println("Invalid withdrawal amount. Please enter a valid number.");
        }
    }

    public void login() {

        //checking if user is already logged in
        if (userIsLoggedIn || !userCreated) {
            System.out.println("You can't perform this action!");
            return;
        }

        //gets username and password
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        // Check if the username and password are matching any of the stored users
        if (takenUsers.containsKey(username) && takenUsers.get(username).equals(password)) {
            userIsLoggedIn = true;
            user = new Account(username, password, userID, 0);
            System.out.println("Login successful! Welcome " + username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public void createSavingsAccount() {

        if (!userIsLoggedIn) {
            System.out.println("You must be logged in to perform this action!");
            return;
        }

        System.out.println("Hello, you are here to create a new savings Account. Do you want to proceed?");
        String proceed = scanner.next();

        if (!proceed.equalsIgnoreCase("yes")) {
            return;
        }

        double interestRate = new Random().nextDouble(1, 10) + 1;
        System.out.printf("\nYour savings account interest Rate would be %d%.", interestRate);
        new SavingsAccount(user.getBalance(), interestRate);
        
        //make it run every 5 seconds in the background, that the user gets the money
        
    }

    public void logout() {
        if (!userIsLoggedIn) {
            System.out.println("You are not logged in.");
        } else {
            userIsLoggedIn = false;
            user = null;
            System.out.println("You have logged out successfully.");
        }
    }

    public void depositMoneyOnOtherAccount() {

        if (!userIsLoggedIn) {
            System.out.println("You must be logged in to perform this action!");
            return;
        }

        int currentMoney = user.getBalance();
        System.out.printf("\nYou currently have %d€.\n", currentMoney);
        System.out.println("Please type the username of the recipient or type 'exit' to cancel the transaction.");
        String transactionUsername = scanner.nextLine();

        if (transactionUsername.equalsIgnoreCase("exit")) {
            System.out.println("Transaction canceled.");
            return;
        }

        // Check if the recipient user exists
        if (!Account.accountExists(transactionUsername)) {
            System.out.println("The specified user does not exist. Transaction canceled.");
            return;
        }

        // Ask for the transaction amount
        System.out.println("How much money would you like to transfer?");
        int transactionAmount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (transactionAmount > 0) {
            if (transactionAmount <= user.getBalance()) {
                // Deduct money from the current user's account
                user.withdrawMoney(transactionAmount);

                // Deposit money into the recipient's account
                Account recipient = Account.getAccount(transactionUsername);
                recipient.depositMoney(transactionAmount);

                System.out.printf("Successfully transferred %d€ to %s. Your new balance is %d€.\n",
                        transactionAmount, transactionUsername, user.getBalance());
            } else {
                System.out.println("Insufficient balance. Please enter a smaller amount.");
            }
        } else {
            System.out.println("Invalid transfer amount. Please enter a positive number.");
        }
    }
}
