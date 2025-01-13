import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //big thanks to @etylermoss for the english swear words list!

        while (true) {
            System.out.println("+-----------------------------------------------+");
            System.out.println("|                 Library Menu                  |");
            System.out.println("|               Select an Option                |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|  1. create a Account                          |");
            System.out.println("|  2. Deposit Money                             |");
            System.out.println("|  3. Withdraw Money                            |");
            System.out.println("+-----------------------------------------------+");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch(userInput) {
                case 1 -> Bank.createAccount();
                case 2 -> Bank.depositMoney();
                case 3 -> Bank.withdrawMoney();
                default -> System.out.println("Please enter a valid number!");
            }
        } //while loop ends here
    }//method ends here
} //class ends here
