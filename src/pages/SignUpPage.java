package pages;

import dal.admins.AdminDAO;

import javax.swing.*;
import java.awt.*;

public class SignUpPage extends JFrame {
    private final AdminDAO adminDao;

    public SignUpPage(AdminDAO adminDao) {
        this.adminDao = adminDao;

        setTitle("Sign Up");
        setSize(350, 180);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        JLabel usernameLabel = new JLabel("New Username:");
        JTextField usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("New Password:");
        JPasswordField passwordField = new JPasswordField(15);
        JButton registerButton = new JButton("Register");

        // Add components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // Action listener
        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both fields are required.");
                return;
            }

            boolean created = adminDao.createAdmin(username, password);
            if (created) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                dispose(); // close sign-up window
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists or error occurred.");
            }
        });

        setVisible(true);
    }
}
