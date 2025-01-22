import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //big thanks to @etylermoss for the english swear words list!

        Bank.takenUsers.put("PaulTheJavaDev", "HelloWorld!"); //very secure password!!

        Bank bank = new Bank();

        while (true) {

            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                System.out.println("Something went wrong: " + e);
            }

            System.out.println("+-----------------------------------------------+");
            System.out.println("|                   Bank Menu                   |");
            System.out.println("|               Select an Option                |");
            System.out.println("+-----------------------------------------------+");
            System.out.println("|  1. create a Account                          |");
            System.out.println("|  2. Login                                     |");
            System.out.println("|  3. Deposit Money                             |");
            System.out.println("|  4. Withdraw Money                            |");
            System.out.println("|  5. Open a new type of Account                |");
            System.out.println("|  6. Deposit Money on another Account          |");
            System.out.println("|  7. Logout                                    |");
            System.out.println("|  8. Exit                                      |");
            System.out.println("+-----------------------------------------------+");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch(userInput) {
                case 1 -> bank.createAccount();
                case 2 -> bank.login();
                case 3 -> bank.depositMoney();
                case 4 -> bank.withdrawMoney();

                case 5 -> bank.createSavingsAccount();

                case 6 -> bank.depositMoneyOnOtherAccount();
                case 7 -> bank.logout();
                case 8 -> System.exit(0);
                default -> System.out.println("Please enter a valid number!");
            }
        } //while loop ends here
    }//method ends here
} //class ends here
