import java.text.DecimalFormat;
import java.util.Scanner;

public class Accounts {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance;
    private double savingBalance;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber(){
        return customerNumber;
    }

    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    public int getPinNumber(){
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance(){
        return savingBalance;
    }

    public double calcCheckingWithdraw(double checkingBalance, double withdrawamount){
        return checkingBalance = (checkingBalance - withdrawamount);
    }

    public void calcSavingWithdraw(double amount){
        savingBalance = (savingBalance - amount);
    }

    public double calcCheckingDeposit(double checkingBalance, double amount){
        return  checkingBalance = (checkingBalance + amount);
    }

    public void calcSavingDeposit(double amount){
        savingBalance = (savingBalance + amount);
    }

    public void getCheckingWithdrawInput(int checkingBalance) {
        System.out.println("Checking Account balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to withdraw from Checking Account: ");
        double amount = input.nextDouble();

        if(checkingBalance - amount >= 0){
            double recentBalance = calcCheckingWithdraw(checkingBalance, amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(recentBalance));
        }
        else{
            System.out.println("Not Enough Money to Withdraw");
        }
    }

    public void getSavingWithdrawInput() {
        System.out.println("Saving Account balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount you want to withdraw from Saving Account: ");
        double amount = input.nextDouble();

        if(savingBalance - amount >= 0){
            calcSavingWithdraw(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
        }
        else{
            System.out.println("Not Enough Money to Withdraw");
        }
    }

    public void getCheckingDepositInput(int checkingBalance){
        System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Amount you want to deposit to Checking Account: ");
        double amount = input.nextDouble();

        if(checkingBalance + amount >= 0){
            double recentBalance = calcCheckingDeposit(checkingBalance, amount);
            System.out.println("New Checking Account Balance: " + moneyFormat.format(recentBalance));
        }
        else{
            System.out.println("No Money to Deposit");
        }
    }

    public void getSavingDepositInput(){
        System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Amount you want to deposit to Saving Account: ");
        double amount = input.nextDouble();

        if(checkingBalance + amount >= 0){
            calcSavingDeposit(amount);
            System.out.println("New Saving Account Balance: " + moneyFormat.format(savingBalance));
        }
        else{
            System.out.println("No Money to Deposit");
        }
    }

}