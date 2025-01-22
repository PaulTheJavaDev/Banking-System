public class SavingsAccount{

    private double balance;
    private double interestRate; //as a percentage

    //Maybe add maximum money for more account options
    SavingsAccount(double balance, double interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
    }

    void checkIn() {
        this.balance = this.balance * (1 + interestRate);
    }



}
