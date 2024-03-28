package Infobyte;

import java.util.Scanner;

interface BankAccount {
  double getBalance();

  boolean withdraw(double amount);

  void deposit(double amount);

  // Added method for transfer
  boolean transfer(double amount, BankAccount recipientAccount);
}

class SavingsAccount implements BankAccount {
  private double balance;

  public SavingsAccount(double balance) {
    this.balance = balance;
  }

  public double getBalance() {
    return balance;
  }

  public boolean withdraw(double amount) {
    if (balance >= amount) {
      balance -= amount;
      return true;
    } else {
      return false;
    }
  }

  public void deposit(double amount) {
    balance += amount;
  }

  @Override
  public boolean transfer(double amount, BankAccount recipientAccount) {
    if (withdraw(amount)) {
      recipientAccount.deposit(amount);
      System.out.println("Transfer successful. $" + amount + " transferred to account.");
      return true;
    } else {
      System.out.println("There are insufficient funds for transfer.");
      return false;
    }
  }
}

class ATM {
  private BankAccount account;

  public ATM(BankAccount account) {
    this.account = account;
  }

  public void withdraw(double amount) {
    if (account.withdraw(amount)) {
      System.out.println("Withdraw successful. The remaining balance in the account is: $" + account.getBalance());
    } else {
      System.out.println("There is no sufficient amount to withdrawal.");
    }
  }

  public void deposit(double amount) {
    account.deposit(amount);
    System.out.println("Deposit successful. Balance in account after deposit : $" + account.getBalance());
  }

  public void checkBalance() {
    System.out.println("Your current account balance is: $" + account.getBalance());
  }

  // Added method for transfer
  public void transfer(double amount, BankAccount recipientAccount) {
    if (account.transfer(amount, recipientAccount)) {
      System.out.println("Transfer successful.");
    } else {
      System.out.println("Transfer failed.");
    }
  }
}

class ATMFactory {
  public static ATM createATM(BankAccount account) {
    return new ATM(account);
  }
}

public class ATMInterface {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    BankAccount userAccount = new SavingsAccount(10000.0);

    ATM atm = ATMFactory.createATM(userAccount);

    while (true) {
      System.out.println("Welcome to the ATM interface.");
      System.out.println("1. Withdraw");
      System.out.println("2. Deposit");
      System.out.println("3. Check Balance");
      System.out.println("4. Transfer");
      System.out.println("5. Exit");

      int choice = input.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Enter the amount to withdraw: $");
          double withdrawAmount = input.nextDouble();
          atm.withdraw(withdrawAmount);
          break;
        case 2:
          System.out.print("Enter the amount to deposit: $");
          double depositAmount = input.nextDouble();
          atm.deposit(depositAmount);
          break;
        case 3:
          atm.checkBalance();
          break;
        case 4:
          System.out.println("Enter the amount to transfer: $");
          double transferAmount = input.nextDouble();
          BankAccount recipientAccount = new SavingsAccount(0.0);
          atm.transfer(transferAmount, recipientAccount);
          break;
        case 5:
          System.out.println("Thank you! Have a nice day");
          System.exit(0);
        default:
          System.out.println("Please choose a correct option.");
      }
    }
  }
}
