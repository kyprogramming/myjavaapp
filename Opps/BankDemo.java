package Opps;

class BankAccount {
    private double balance;   // hidden — no direct access

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class BankDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount();
        acc.deposit(1000);
        acc.deposit(500);
        System.out.println("Balance: " + acc.getBalance());
    }
}