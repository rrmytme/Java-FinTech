import java.text.DecimalFormat;
import java.util.Scanner;

public class AccountOptions extends Accounts {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    // simulate json file as db
    String filePath = "accountsDB.json";
    private static int account;
    private static int pin;

    public void getLogin() {
        int x = 1;
        do{
            try{
                System.out.println("Welcome to JAVA Bank");
                System.out.println("Existing customer :) [YES/NO]?");
                String customerStatus = menuInput.nextLine();
                if(customerStatus.equalsIgnoreCase("yes")){
                    System.out.println("Enter your Customer Number");
                    account = menuInput.nextInt();
                    System.out.println("Enter your PIN Number");
                    pin = menuInput.nextInt();

                    boolean isValidCustomer = Utils.validateAccount(filePath, account, pin);
                    if(isValidCustomer){
                        getAccountType();
                    }
                    else{
                        System.out.println("\nWrong Customer Number or Wrong PIN Number\n\n");
                    }
                }
                else
                {
                    System.out.println("Would like to join our family? [YES/NO]");
                    String customerPreference = menuInput.nextLine();
                    if(customerPreference.equalsIgnoreCase("yes")) {
                        Customers.customerManager();
                    }
                    else{
                        System.out.println("Thanks, see you again");
                        System.exit(0);
                    }
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
            case 3 -> {
                System.out.println("Exiting...");
                menuInput.close();
                System.exit(0);
            }
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
                getCheckingWithdrawInput(filePath, account, pin, accountBalance);
                getAccountType();
            }
            case 3 -> {
                getCheckingDepositInput(filePath, account, pin, accountBalance);
                getAccountType();
            }
            case 4 -> {
                System.out.println("Exiting...");
                menuInput.close();
                System.exit(0);
            }
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
        int accountBalance = Utils.getAccountBalance(filePath, account, pin);

        switch (selection) {
            case 1 -> {
                System.out.println("Saving Account Balance: " + moneyFormat.format(accountBalance));
                getAccountType();
            }
            case 2 -> {
                getSavingWithdrawInput(filePath, account, pin, accountBalance);
                getAccountType();
            }
            case 3 -> {
                getSavingDepositInput(filePath, account, pin, accountBalance);
                getAccountType();
            }
            case 4 -> {
                System.out.println("Exiting...");
                menuInput.close();
                System.exit(0);
            }
            default -> {
                System.out.println("\nInvalid Choice\n");
                getChecking();
            }
        }
    }
}