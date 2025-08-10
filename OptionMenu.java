import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
// import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Account> data = new HashMap<>();

    public void mainMenu() throws IOException {
        // Preload demo accounts
        data.put(952141, new Account(952141, 191904, 1000, 5000));
        data.put(123, new Account(123, 123, 20000, 50000));

        boolean end = false;
        while (!end) {
            System.out.println("\n Type 1 - Login");
            System.out.println(" Type 2 - Create Account");
            System.out.println(" Type 3 - Exit");
            System.out.print("\nChoice: ");
            try {
                int choice = menuInput.nextInt();
                switch (choice) {
                    case 1:
                        getLogin();
                        break;
                    case 2:
                        createAccount();
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nThank You for using this ATM.\n");
        menuInput.close();
        System.exit(0);
    }

    private void getLogin() {
        boolean end = false;
        while (!end) {
            try {
                System.out.print("\nEnter your customer number: ");
                int customerNumber = menuInput.nextInt();
                System.out.print("Enter your PIN number: ");
                int pinNumber = menuInput.nextInt();

                Account acc = data.get(customerNumber);
                if (acc != null && acc.getPinNumber() == pinNumber) {
                    getAccountType(acc);
                    end = true;
                } else {
                    System.out.println("\nWrong Customer Number or Pin Number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Character(s). Only Numbers.");
                menuInput.next();
            }
        }
    }

    private void getAccountType(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSelect the account you want to access:");
                System.out.println(" Type 1 - Checking Account");
                System.out.println(" Type 2 - Savings Account");
                System.out.println(" Type 3 - Logout");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    private void getChecking(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nChecking Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds to Savings");
                System.out.println(" Type 5 - Back");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nChecking Account Balance: " +
                                moneyFormat.format(acc.getCheckingBalance()));
                        break;
                    case 2:
                        System.out.print("\nAmount to withdraw: ");
                        acc.withdrawChecking(menuInput.nextDouble());
                        break;
                    case 3:
                        System.out.print("\nAmount to deposit: ");
                        acc.depositChecking(menuInput.nextDouble());
                        break;
                    case 4:
                        System.out.print("\nAmount to transfer: ");
                        acc.transferFromCheckingToSaving(menuInput.nextDouble());
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    private void getSaving(Account acc) {
        boolean end = false;
        while (!end) {
            try {
                System.out.println("\nSavings Account: ");
                System.out.println(" Type 1 - View Balance");
                System.out.println(" Type 2 - Withdraw Funds");
                System.out.println(" Type 3 - Deposit Funds");
                System.out.println(" Type 4 - Transfer Funds to Checking");
                System.out.println(" Type 5 - Back");
                System.out.print("\nChoice: ");

                int selection = menuInput.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("\nSavings Account Balance: " +
                                moneyFormat.format(acc.getSavingBalance()));
                        break;
                    case 2:
                        System.out.print("\nAmount to withdraw: ");
                        acc.withdrawSaving(menuInput.nextDouble());
                        break;
                    case 3:
                        System.out.print("\nAmount to deposit: ");
                        acc.depositSaving(menuInput.nextDouble());
                        break;
                    case 4:
                        System.out.print("\nAmount to transfer: ");
                        acc.transferFromSavingToChecking(menuInput.nextDouble());
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
    }

    private void createAccount() {
        boolean end = false;
        while (!end) {
            try {
                System.out.print("\nEnter your new customer number: ");
                int cst_no = menuInput.nextInt();

                if (data.containsKey(cst_no)) {
                    System.out.println("\nThis customer number is already registered.");
                } else {
                    System.out.print("Enter PIN to be registered: ");
                    int pin = menuInput.nextInt();
                    data.put(cst_no, new Account(cst_no, pin));
                    System.out.println("\nYour new account has been successfully registered!");
                    end = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid Choice.");
                menuInput.next();
            }
        }
        System.out.println("\nRedirecting to login…");
        getLogin();
    }
}
