public class Account {
    private String username;
    private String password;
    private String userID;
    private int balance;

    public Account(String username, String password, String userID, int balance) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void depositMoney(int amount) {
        this.balance += amount;
    }

    public void withdrawMoney(int amount) {

    }

    public String getUsername() {
        return username;
    }

    public String getUserID() {
        return userID;
    }
}
