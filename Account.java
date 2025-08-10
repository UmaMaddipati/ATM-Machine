import java.text.DecimalFormat;
// import java.util.InputMismatchException;
// import java.util.Scanner;

public class Account {
    // variables
    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    private static final DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public Account() {
    }

    public Account(int customerNumber, int pinNumber) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
    }

    public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
        this.customerNumber = customerNumber;
        this.pinNumber = pinNumber;
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    // Getters / setters
    public int getCustomerNumber() {
        return customerNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    // Core operations
    public void withdrawChecking(double amount) {
        if (amount >= 0 && checkingBalance >= amount) {
            checkingBalance -= amount;
            System.out.println("Checking Withdraw successful. New balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds in Checking.");
        }
    }

    public void depositChecking(double amount) {
        if (amount >= 0) {
            checkingBalance += amount;
            System.out.println("Checking Deposit successful. New balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transferFromCheckingToSaving(double amount) {
        if (amount >= 0 && checkingBalance >= amount) {
            checkingBalance -= amount;
            savingBalance += amount;
            System.out.println("Transferred " + moneyFormat.format(amount) + " to Savings. Checking: " +
                    moneyFormat.format(checkingBalance) + ", Savings: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds to transfer from Checking.");
        }
    }

    public void withdrawSaving(double amount) {
        if (amount >= 0 && savingBalance >= amount) {
            savingBalance -= amount;
            System.out.println("Savings Withdraw successful. New balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds in Savings.");
        }
    }

    public void depositSaving(double amount) {
        if (amount >= 0) {
            savingBalance += amount;
            System.out.println("Savings Deposit successful. New balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transferFromSavingToChecking(double amount) {
        if (amount >= 0 && savingBalance >= amount) {
            savingBalance -= amount;
            checkingBalance += amount;
            System.out.println("Transferred " + moneyFormat.format(amount) + " to Checking. Savings: " +
                    moneyFormat.format(savingBalance) + ", Checking: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Invalid amount or insufficient funds to transfer from Savings.");
        }
    }
}
