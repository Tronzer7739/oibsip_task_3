import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMinterface {
    private double baseAmount = 0;
    private StringBuilder History = new StringBuilder();
    private JTextArea Area;
    private JFrame frame;
    private JFrame passwordFrame;

    public ATMinterface() {

        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);



        JPanel mainPanel = new JPanel(new GridBagLayout());
        frame.add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        JLabel amountLabel = new JLabel("Enter the amount:");

        JTextField amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        JButton viewBalanceButton = new JButton("View Balance");

        JLabel transferLabel = new JLabel("Transfer to:");

        JTextField transferToField = new JTextField();
        JButton transferButton = new JButton("Transfer");

        JButton logoutButton = new JButton("Logout");

        gbc.gridy = 6;
        mainPanel.add(logoutButton, gbc);


        Area = new JTextArea();
       Area.setEditable(false);

        Area.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(Area);



        depositButton.setBackground(Color.GREEN);
        withdrawButton.setBackground(Color.RED);



        transferButton.setBackground(Color.GREEN);
        Area.setBackground(new Color(32, 255, 0));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        mainPanel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(amountField, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;

        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(depositButton, gbc);

        gbc.gridx = 2;
        mainPanel.add(withdrawButton, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;

        mainPanel.add(viewBalanceButton, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 1;

        mainPanel.add(transferLabel, gbc);


        gbc.gridx = 1;
        gbc.gridwidth = 2;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(transferToField, gbc);


        gbc.gridy = 4;

        gbc.gridx = 1;

        gbc.gridwidth = 2;
        mainPanel.add(transferButton, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;

        gbc.gridwidth = 3;

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);



        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = amountField.getText();
                try {
                    double depositAmount = Double.parseDouble(amountStr);
                    baseAmount += depositAmount;
                    updateHistory("Deposit: " + depositAmount + " Rupees");
                    JOptionPane.showMessageDialog(frame, "Deposit successful! New balance: " + baseAmount + " Rupees");
                    amountField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = amountField.getText();
                try {
                    double withdrawAmount = Double.parseDouble(amountStr);
                    if (withdrawAmount > baseAmount) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds. You can't withdraw more than your balance.");
                    } else {
                        baseAmount -= withdrawAmount;
                        updateHistory("Withdraw: " + withdrawAmount + " Rupees");
                        JOptionPane.showMessageDialog(frame, "Withdraw successful! New balance: " + baseAmount + " Rupees");
                        amountField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.");
                }
            }
        });

        viewBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Current Balance: " + baseAmount + " Rupees");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transferAmountStr = amountField.getText();
                String transferTo = transferToField.getText();
                try {
                    double transferAmount = Double.parseDouble(transferAmountStr);
                    if (transferAmount > baseAmount) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds. You can't transfer more than your balance.");
                    } else {
                        baseAmount -= transferAmount;
                        updateHistory("Transfer to " + transferTo + ": " + transferAmount + " Rupees");
                        JOptionPane.showMessageDialog(frame, "Transfer successful! New balance: " + baseAmount + " Rupees");
                        amountField.setText("");
                        transferToField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount. Please enter a valid number.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordFrame = new JFrame("Login");
                passwordFrame.setSize(500, 300);
                passwordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JPanel panel = new JPanel();
                passwordFrame.add(panel);
                panel.setLayout(null); // Use absolute positioning

                JLabel label = new JLabel("User");
                label.setBounds(10, 20, 80, 25);
                panel.add(label);

                JTextField userText = new JTextField(10);
                userText.setBounds(100, 20, 165, 25);
                panel.add(userText);

                JLabel passwordLabel = new JLabel("Password");
                passwordLabel.setBounds(10, 50, 80, 25);
                panel.add(passwordLabel);

                JPasswordField passwordText = new JPasswordField();
                passwordText.setBounds(100, 50, 165, 25);
                panel.add(passwordText);

                JLabel success = new JLabel();
                success.setBounds(10, 110, 300, 25);
                panel.add(success);

                JButton button = new JButton("LOGIN");

                button.setBounds(10, 80, 80, 25);
                panel.add(button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String user = userText.getText();
                        String password = new String(passwordText.getPassword());

                        if (user.equals("Rajveer") && password.equals("77648")) {
                            success.setText("Successfully Logged In");
                            passwordFrame.dispose();
                            frame.setVisible(true);
                        } else {
                            success.setText("Invalid username or password");
                        }
                    }
                });

                passwordFrame.setLocationRelativeTo(null);
                passwordFrame.setVisible(true);
                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateHistory(String transaction) {
        History.append(transaction).append("\n");

        Area.setText(History.toString());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new ATMinterface();
            }
        });
    }
}