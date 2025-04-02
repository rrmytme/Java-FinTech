import java.text.DecimalFormat;
import java.util.Scanner;

public class Accounts {
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public void getCheckingWithdrawInput(String filePath, int accountNumber, int pin, int checkingBalance) {
        System.out.println("Checking Account balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to withdraw from Checking Account: ");
        double amount = input.nextDouble();
        Utils.withdraw(filePath, accountNumber, pin, amount);
    }

    public void getSavingWithdrawInput(String filePath, int accountNumber, int pin, int checkingBalance) {
        System.out.println("Saving Account balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to withdraw from Saving Account: ");
        double amount = input.nextDouble();
        Utils.withdraw(filePath, accountNumber, pin, amount);
    }

    public void getCheckingDepositInput(String filePath, int accountNumber, int pin, int checkingBalance){
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to deposit to Checking Account: ");
        double amount = input.nextDouble();
        Utils.deposit(filePath, accountNumber, pin, amount);
    }

    public void getSavingDepositInput(String filePath, int accountNumber, int pin, int checkingBalance){
        System.out.println("Saving Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to deposit to Saving Account: ");
        double amount = input.nextDouble();
        Utils.deposit(filePath, accountNumber, pin, amount);
    }
}