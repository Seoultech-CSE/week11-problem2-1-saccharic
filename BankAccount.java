import java.util.Scanner;
import java.util.InputMismatchException;

// 1. Custom Checked Exception Class
// TODO: Inherit from the correct class to make this a Checked Exception.
class InsufficientBalanceException extends Exception { 
    private double balance;
    private double amount;

    public InsufficientBalanceException(double balance, double amount) {
        // TODO: Invoke the superclass constructor with a clear error message[cite: 289].
        super("Invaild balance " + balance);
        this.balance = balance;
        this.amount = amount;
    }

    public double getBalance() { return balance; }
    public double getAmount() { return amount; }
}

// 2. Integrated Core Class
public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        // TODO: Validate input and throw an IllegalArgumentException if amount is <= 0[cite: 102].
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }
        
        balance += amount;
        System.out.println("$" + amount + " successfully deposited.");
    }

    // TODO: Add the proper exception declaration to the method signature[cite: 95].
    public void withdraw(double amount) throws InsufficientBalanceException {
        // TODO: Validate balance and throw your custom InsufficientBalanceException if needed[cite: 110].
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        
        balance -= amount;
        System.out.println("$" + amount + " successfully withdrawn.");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BankAccount account = new BankAccount(500.0); 

        System.out.println("=== Welcome to the Interactive Banking System ===");
        System.out.println("Initial Balance: $500.0");

        // --- DEPOSIT PROCESS ---
        // TODO: Wrap the deposit process in a try-catch-finally layout[cite: 34].
        // Catch InputMismatchException and IllegalArgumentException, and always display the balance[cite: 44, 151].
        System.out.print("\nEnter the amount to DEPOSIT: ");
        try {
            double depositAmount = input.nextDouble();
            account.deposit(depositAmount);
        } catch (InputMismatchException ex1) {
            System.out.println("Error : Invaild input");
            input.nextLine();
        } catch (IllegalArgumentException ex2) {
            System.out.println("Error : Invaild number");
            input.nextLine();
        } finally {
            System.out.println("Your current balance is " + account.balance + " dollar(s).");
        }

        // --- WITHDRAWAL PROCESS ---
        // TODO: Wrap the withdrawal process in a try-catch-finally layout[cite: 34].
        // Catch InputMismatchException and InsufficientBalanceException, and always display the balance[cite: 44, 151].
        System.out.print("\nEnter the amount to WITHDRAW: ");
        try {
            double withdrawAmount = input.nextDouble();
            account.withdraw(withdrawAmount);
        } catch (InputMismatchException ex1) {
            System.out.println("Error : Invaild input");
            input.nextLine();
        } catch (InsufficientBalanceException ex2) {
            System.out.println("Error : Insufficient Balance");
            input.nextLine();
        } finally {
            System.out.println("Your current balance is " + account.balance + " dollar(s).");
        }

        System.out.println("\n=== Thank you for using our service ===");
        input.close();
    }
}