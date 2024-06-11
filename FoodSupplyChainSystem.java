/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package foodsupplychainsystem;
/**
 *
 * @author RIMSHA
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodSupplyChainSystem {
    private static ArrayList<User> users = new ArrayList<>();
    private static User currentUser;
    private static Cart cart = new Cart();
    private static ArrayList<SmartContract> smartContracts = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FoodSupplyChainSystem::createAndShowGUI);
    }
    
    //GUI Method
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Food Supply Chain System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        MainPanel mainPanel = new MainPanel();
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }
    
    //Main Panel Class
    static class MainPanel extends JPanel {
        private CardLayout cardLayout;
        private JPanel cards;

        public MainPanel() {
            cardLayout = new CardLayout();
            cards = new JPanel(cardLayout);

            WelcomePanel welcomePanel = new WelcomePanel(this);
            UserMenuPanel userMenuPanel = new UserMenuPanel(this);
            SmartContractsPanel smartContractsPanel = new SmartContractsPanel(this);

            cards.add(welcomePanel, "Welcome");
            cards.add(userMenuPanel, "UserMenu");
            cards.add(smartContractsPanel, "SmartContracts");

            setLayout(new BorderLayout());
            add(cards, BorderLayout.CENTER);
        }

        public void showCard(String cardName) {
            cardLayout.show(cards, cardName);
        }
    }
    
    //Welcom panel Class
    static class WelcomePanel extends JPanel {
        public WelcomePanel(MainPanel mainPanel) {
            setLayout(new GridLayout(4, 1));

            JLabel welcomeLabel = new JLabel("Welcome to Food Supply Chain System", SwingConstants.CENTER);
            JButton loginButton = new JButton("Login");
            JButton signUpButton = new JButton("Sign Up");
            JButton exitButton = new JButton("Exit");

            loginButton.addActionListener(e -> showLoginDialog(mainPanel));
            signUpButton.addActionListener(e -> showSignUpDialog(mainPanel));
            exitButton.addActionListener(e -> System.exit(0));

            add(welcomeLabel);
            add(loginButton);
            add(signUpButton);
            add(exitButton);
        }

        private void showLoginDialog(MainPanel mainPanel) {
            new LoginDialog(mainPanel).setVisible(true);
        }

        private void showSignUpDialog(MainPanel mainPanel) {
            new SignUpDialog(mainPanel).setVisible(true);
        }
    }

    //Login dialog
    static class LoginDialog extends JDialog {
        public LoginDialog(MainPanel mainPanel) {
            setTitle("Login");
            setSize(300, 200);
            setLayout(new GridLayout(3, 2));

            JLabel emailLabel = new JLabel("Email:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");
            JButton cancelButton = new JButton("Cancel");

            loginButton.addActionListener(e -> {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                for (User user : users) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        currentUser = user;
                        JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + currentUser.getName());
                        mainPanel.showCard("UserMenu");
                        dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "Invalid email or password. Please try again.");
            });

            cancelButton.addActionListener(e -> dispose());

            add(emailLabel);
            add(emailField);
            add(passwordLabel);
            add(passwordField);
            add(loginButton);
            add(cancelButton);
        }
    }
    
    //SignUp Dialog
    static class SignUpDialog extends JDialog {
        public SignUpDialog(MainPanel mainPanel) {
            setTitle("Sign Up");
            setSize(300, 300);
            setLayout(new GridLayout(5, 2));

            JLabel nameLabel = new JLabel("Name:");
            JLabel emailLabel = new JLabel("Email:");
            JLabel passwordLabel = new JLabel("Password:");
            JTextField nameField = new JTextField();
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            JLabel roleLabel = new JLabel("Role:");
            String[] roles = {"Admin", "Producer", "Distributor"};
            JComboBox<String> roleComboBox = new JComboBox<>(roles);
            JButton signUpButton = new JButton("Sign Up");
            JButton cancelButton = new JButton("Cancel");

            signUpButton.addActionListener(e -> {
                String name = nameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String role = (String) roleComboBox.getSelectedItem();

                User newUser;
                switch (role) {
                    case "Admin":
                        newUser = new Admin(name, email, password);
                        break;
                    case "Producer":
                        newUser = new Producer(name, email, password);
                        break;
                    case "Distributor":
                        newUser = new Distributor(name, email, password);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Invalid role choice. Sign up failed.");
                        return;
                }

                users.add(newUser);
                currentUser = newUser;
                JOptionPane.showMessageDialog(this, "Sign up successful. Welcome, " + currentUser.getName());
                mainPanel.showCard("UserMenu");
                dispose();
            });

            cancelButton.addActionListener(e -> dispose());

            add(nameLabel);
            add(nameField);
            add(emailLabel);
            add(emailField);
            add(passwordLabel);
            add(passwordField);
            add(roleLabel);
            add(roleComboBox);
            add(signUpButton);
            add(cancelButton);
        }
    }

    static class UserMenuPanel extends JPanel {
        public UserMenuPanel(MainPanel mainPanel) {
            setLayout(new GridLayout(7, 1));

            JButton addProductsButton = new JButton("Add Products to Cart");
            JButton assetTrackingButton = new JButton("Asset Tracking");
            JButton blockchainTransactionsButton = new JButton("Blockchain Transactions");
            JButton smartContractsButton = new JButton("Smart Contracts");
            JButton viewWalletButton = new JButton("My Wallet");
            JButton logoutButton = new JButton("Logout");

            addProductsButton.addActionListener(e -> addProductsToCart());
            assetTrackingButton.addActionListener(e -> assetTracking());
            blockchainTransactionsButton.addActionListener(e -> blockchainTransactions(mainPanel));
            smartContractsButton.addActionListener(e -> mainPanel.showCard("SmartContracts"));
            viewWalletButton.addActionListener(e -> viewWallet());
            logoutButton.addActionListener(e -> {
                currentUser = null;
                mainPanel.showCard("Welcome");
            });

            add(new JLabel("User Menu", SwingConstants.CENTER));
            add(addProductsButton);
            add(assetTrackingButton);
            add(blockchainTransactionsButton);
            add(smartContractsButton);
            add(viewWalletButton);
            add(logoutButton);
        }

        private void addProductsToCart() {
            // Implementation for adding products to cart
            JOptionPane.showMessageDialog(this, "Add Products to Cart (functionality not yet implemented).");
        }

        private void assetTracking() {
            // Implementation for asset tracking
            JOptionPane.showMessageDialog(this, "Asset Tracking (functionality not yet implemented).");
        }

        private void blockchainTransactions(MainPanel mainPanel) {
            new BlockchainTransactionsDialog(mainPanel).setVisible(true);
        }

        private void viewWallet() {
            // Implementation for viewing wallet
            JOptionPane.showMessageDialog(this, "Viewing Wallet (functionality not yet implemented).");
        }
    }

    static class BlockchainTransactionsDialog extends JDialog {
        public BlockchainTransactionsDialog(MainPanel mainPanel) {
            setTitle("Blockchain Transactions");
            setSize(300, 200);
            setLayout(new GridLayout(3, 1));

            JButton createTransactionButton = new JButton("Create Transaction");
            JButton viewTransactionsButton = new JButton("View Transactions");
            JButton closeButton = new JButton("Close");

            createTransactionButton.addActionListener(e -> createTransaction());
            viewTransactionsButton.addActionListener(e -> viewTransactions());
            closeButton.addActionListener(e -> dispose());

            add(createTransactionButton);
            add(viewTransactionsButton);
            add(closeButton);
        }

        private void createTransaction() {
            // Implementation for creating transaction
            JOptionPane.showMessageDialog(this, "Create Transaction (functionality not yet implemented).");
        }

        private void viewTransactions() {
            // Implementation for viewing transactions
            JOptionPane.showMessageDialog(this, "View Transactions (functionality not yet implemented).");
        }
    }

    static class SmartContractsPanel extends JPanel {
        private MainPanel mainPanel;
        private Map<String, String> termsAndConditionsMap;

        public SmartContractsPanel(MainPanel mainPanel) {
            this.mainPanel = mainPanel;
            setLayout(new BorderLayout());

            // Initialize the map with terms and conditions for each smart contract
            termsAndConditionsMap = new HashMap<>();
            termsAndConditionsMap.put("Contract 1", "Terms and conditions\n" +
                    " Terms and conditions\n" +
                    " 1. Purpose\n" +
                    " 1.1. Provide transparent, immutable \n" +
                    "traceability of food products.\n" +
                    " 2. Execution\n" +
                    " 2.1. Executed upon blockchain \n" +
                    "confirmation, recording origins, \n" +
                    "timestamps, and locations.\n" +
                    " 3. Conditions\n" +
                    " 3.1. Verification of relevant data \n" +
                    "required.\n" +
                    " 3.2. Changes must be agreed upon by all \n" +
                    "Parties and recorded on the blockchain.\n" +
                    " 4. Representations and Warranties\n" +
                    " 4.1. Parties must have legal authority \n" +
                    "and provide accurate data.\n" +
                    " 5. Liability\n" +
                    " 5.1. Parties liable for their actions; no \n" +
                    "indirect damages.\n" );
            
            termsAndConditionsMap.put("Contract 2", " Terms and conditions\n" +
                        " Terms and conditions\n" +
                        " 1. Purpose\n" +
                        " 1.1. Ensure food quality and safety \n" +
                        "through quality assurance measures.\n" +
                        " 2. Execution\n" +
                        " 2.1. Executed upon blockchain \n" +
                        "confirmation, recording inspections, \n" +
                        "tests, and certifications.\n" +
                        " 3. Conditions\n" +
                        " 3.1. Verification of quality assurance \n" +
                        "data required.\n" +
                        " 3.2. Changes must be agreed upon by all \n" +
                        "Parties and recorded on the blockchain.\n" +
                        " 4. Representations and Warranties\n" +
                        " 4.1. Parties must have legal authority \n" +
                        "and comply with quality standards.\n" +
                        " 5. Liability\n" +
                        " 5.1. Parties liable for their actions; no \n" +
                        "indirect damages.\n" );

            JPanel contractsPanel = new JPanel(new GridLayout(termsAndConditionsMap.size(), 1));
            termsAndConditionsMap.forEach((contractName, terms) -> {
                JButton contractButton = new JButton(contractName);
                contractButton.addActionListener(e -> showContractDetails(contractName, terms));
                contractsPanel.add(contractButton);
            });

            add(new JLabel("Smart Contracts", SwingConstants.CENTER), BorderLayout.NORTH);
            add(contractsPanel, BorderLayout.CENTER);
        }

        private void showContractDetails(String contractName, String terms) {
            JOptionPane.showMessageDialog(this, terms, contractName, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}






