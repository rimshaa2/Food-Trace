/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodsupplychainsystem;

/**
 *
 * @author RIMSHA
 */
import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String transactionId;
    private User fromUser;
    private User toUser;
    private double amount;
    private String date;

    private static ArrayList<Transaction> blockchain = new ArrayList<>();

    public Transaction(String transactionId, User fromUser, User toUser, double amount, String date) {
        this.transactionId = transactionId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.date = date;
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("From: " + fromUser.getName());
        System.out.println("To: " + toUser.getName());
        System.out.println("Amount: $" + amount);
        System.out.println("Date: " + date);
    }

    public static void createTransaction(Scanner scanner, ArrayList<User> users, User currentUser) {
        System.out.print("Transaction ID: ");
        String transactionId = scanner.nextLine();
        System.out.print("Recipient Email: ");
        String recipientEmail = scanner.nextLine();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        System.out.print("Date: ");
        scanner.nextLine(); // Consume newline
        String date = scanner.nextLine();

        User recipient = null;
        for (User user : users) {
            if (user.getEmail().equals(recipientEmail)) {
                recipient = user;
                break;
            }
        }

        if (recipient == null) {
            System.out.println("Recipient not found.");
            return;
        }

        Transaction transaction = new Transaction(transactionId, currentUser, recipient, amount, date);
        blockchain.add(transaction);
        System.out.println("Transaction created successfully.");
    }

    public static void viewTransactions() {
        if (blockchain.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : blockchain) {
                transaction.displayTransactionDetails();
            }
        }
    }
}



