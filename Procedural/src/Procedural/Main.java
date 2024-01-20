package Procedural;

import java.util.Scanner;

public class Main
{
    static String username = "";
    static double checkingBalance = 1000;
    static double savingsBalance = 1000;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        if (performLogin(scanner))
        {
            displayMenu();
            ATM_OPERATIONS(scanner);
        }
        else
        {
            System.out.println("Exiting the banking system.");
        }
    }

    public static boolean performLogin(Scanner scanner)
    {
        int attempts = 0;

        while (attempts < 3)
        {
            System.out.print("Enter username: ");
            username = scanner.next();

            System.out.print("Enter password: ");
            String password = scanner.next();

            if ("admin".equals(username) && "admin".equals(password))
            {
                System.out.println("Login successful!\n");
                return true;
            }
            else
            {
                System.out.println("Invalid username or password. Please try again.");
                attempts++;
            }
        }
        System.out.println("Exceeded maximum login attempts. Exiting the ATM.\nThank you for choosing us");
        return false;
    }

    public static void displayMenu()
    {
        System.out.println("ATM Menu:");
        System.out.println("1. Display Checking Account Information");
        System.out.println("2. Deposit to Checking Account");
        System.out.println("3. Withdraw from Checking Account");
        System.out.println("4. Display Savings Account Information");
        System.out.println("5. Deposit to Savings Account");
        System.out.println("6. Withdraw from Savings Account");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: \n");
    }

    public static void ATM_OPERATIONS(Scanner scanner)
    {
        while (true)
        {
            int choice = scanner.nextInt();

            switch (choice)
            {
                case 1:
                    displayAccountInfo("Checking Account", "checkingBalance");
                    break;
                case 2:
                    deposit(scanner, "Checking Account", "checkingBalance");
                    break;
                case 3:
                    withdraw(scanner, "Checking Account", "checkingBalance");
                    break;
                case 4:
                    displayAccountInfo("Savings Account", "savingsBalance");
                    break;
                case 5:
                    deposit(scanner, "Savings Account", "savingsBalance");
                    break;
                case 6:
                    withdraw(scanner, "Savings Account", "savingsBalance");
                    break;
                case 7:
                    System.out.println("Exiting the ATM. Thank you for choosing us!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    private static void displayAccountInfo(String accountType, String account)
    {

        double balance = account.equals("checkingBalance") ? checkingBalance : savingsBalance;

        System.out.println("Username: " + username);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + balance);
        System.out.println();
        displayMenu();
    }
    public static void deposit(Scanner scanner, String accountType, String account)
    {
        System.out.print("Enter the deposit amount to " + accountType + ": $");
        double depositAmount = scanner.nextDouble();

        if (account.equals("checkingBalance")) checkingBalance += depositAmount;
        else savingsBalance += depositAmount;

        System.out.println("Deposit to " + accountType + " successful!");
        displayAccountInfo(accountType, account);
    }
    private static void withdraw(Scanner scanner, String accountType, String account)
    {
        double balance = account.equals("checkingBalance") ? checkingBalance : savingsBalance;

        System.out.print("Enter the withdrawal amount from " + accountType + ": $");
        double withdrawAmount = scanner.nextDouble();

        if (withdrawAmount > balance)
        {
            System.out.println("Insufficient funds!");
            System.out.println();
            displayMenu();
        }
        else
        {
            if (account.equals("checkingBalance")) checkingBalance -= withdrawAmount;
            else savingsBalance -= withdrawAmount;

            System.out.println("Withdrawal from " + accountType + " successful!");
            displayAccountInfo(accountType, account);
        }
    }
}