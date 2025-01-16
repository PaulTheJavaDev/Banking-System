import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //big thanks to @etylermoss for the english swear words list!

        Bank.takenUsers.put("PaulTheJavaDev", "HelloWorld!"); //very secure password!!

        while (true) {
            System.out.println("+-----------------------------------------------+");
            System.out.println("|                   Bank Menu                   |");
            System.out.println("|               Select an Option                |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|  1. create a Account                          |");
            System.out.println("|  2. Login                                     |");
            System.out.println("|  3. Logout                                    |");
            System.out.println("|  4. Deposit Money                             |");
            System.out.println("|  5. Withdraw Money                            |");
            System.out.println("|  6. Deposit Money on another Account          |");
            System.out.println("+-----------------------------------------------+");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch(userInput) {
                case 1 -> Bank.createAccount();
                case 2 -> Bank.login();
                case 3 -> Bank.depositMoney();
                case 4 -> Bank.withdrawMoney();
                case 5 -> Bank.depositMoneyOnOtherAccount();
                case 6 -> Bank.logout();
                default -> System.out.println("Please enter a valid number!");
            }
        } //while loop ends here
    }//method ends here
} //class ends here
