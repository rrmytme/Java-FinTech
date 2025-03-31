import java.text.DecimalFormat;
import java.util.Scanner;

public class AccountOptions extends Accounts {

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    // simulate json file as db
    String filePath = "accountsDB.json";
    int account;
    int pin;

    public void getLogin() {
        int x = 1;
        do{
            try{
                System.out.println("Welcome to ATM");
                System.out.println("Enter your Customer Number");
                account = menuInput.nextInt();
                System.out.println("Enter your PIN Number");
                pin = menuInput.nextInt();

                Boolean isValidCustomer = Utils.validateAccount(filePath, account, pin);
                if(isValidCustomer){
                    getAccountType();
                }
                else{
                    System.out.println("\nWrong Customer Number or Wrong PIN Number\n\n");
                }
            }
            catch(Exception e){
                System.out.println("\nInvalid Characters Only Numbers Allowed\n" + e);
                x = 2;
            }
        }while(x == 1);
    }

    public void getAccountType() {
        System.out.println("Select Account Type you want to Access");
        System.out.println("Type 1 - Checking Account");
        System.out.println("Type 2 - Savings Account");
        System.out.println("Type 3 - Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> getChecking();
            case 2 -> getSaving();
            case 3 -> System.out.println("Thank you for using ATM, BYE\n");
            default -> System.out.println("\n Invalid Choice \n");
        }
    }


    public void getChecking() {
        System.out.println("Checking Account");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");

        int selection = menuInput.nextInt();
        int accountBalance = Utils.getAccountBalance(filePath, account, pin);

        switch (selection) {
            case 1 -> {
                System.out.println("Checking Account Balance: " + moneyFormat.format(accountBalance));
                getAccountType();
            }
            case 2 -> {
                getCheckingWithdrawInput(accountBalance);
                getAccountType();
            }
            case 3 -> {
                getCheckingDepositInput(accountBalance);
                getAccountType();
            }
            case 4 -> System.out.println("Thank you for using ATM, Bye");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getChecking();
            }
        }
    }

    public void getSaving() {
        System.out.println("Saving Account");
        System.out.println("Type 1 - View Balance");
        System.out.println("Type 2 - Withdraw Money");
        System.out.println("Type 3 - Deposit Funds");
        System.out.println("Type 4 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> {
                System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
                getAccountType();
            }
            case 2 -> {
                getSavingWithdrawInput();
                getAccountType();
            }
            case 3 -> {
                getSavingDepositInput();
                getAccountType();
            }
            case 4 -> System.out.println("Thank you for using ATM, Bye\n");
            default -> {
                System.out.println("\nInvalid Choice\n");
                getChecking();
            }
        }
    }
}