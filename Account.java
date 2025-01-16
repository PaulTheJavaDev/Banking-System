import java.util.HashMap;

public class Account {
    private final String username;
    private final String password;
    private final String userID;
    private int balance;

    // A static map to manage all accounts
    private static HashMap<String, Account> accounts = new HashMap<>();

    // Constructor
    public Account(String username, String password, String userID, int balance) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.balance = balance;
    }

    //Explain themselves
    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    //deposit money 🤯
    public void depositMoney(int amount) {
        this.balance += amount;
    }

    //withdraw money duhh
    public void withdrawMoney(int amount) {
        this.balance -= amount;
    }

    //Check if an account exists
    public static boolean accountExists(String username) {
        return accounts.containsKey(username);
    }

    //Get an account by username
    public static Account getAccount(String username) {
        return accounts.get(username);
    }

    //Validate login
    public static boolean validateLogin(String username, String password) {
        Account account = accounts.get(username);
        return account != null && account.getPassword().equals(password);
    }
}
