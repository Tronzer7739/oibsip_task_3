import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
class PasswordValidation implements ActionListener {

    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JLabel success;

    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("User");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        userText = new JTextField(10);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton button = new JButton("LOGIN");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);
        button.addActionListener(new PasswordValidation());

        success = new JLabel();
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = new String(passwordText.getPassword());

        if (user.equals("Rajveer") && password.equals("77648")) {
            success.setText("Successfully Logged In");
            openATMApp();
        } else {
            success.setText("Invalid username or password");
        }
    }

    private void openATMApp() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(success);
        frame.dispose();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMinterface();
            }
        });
    }
}