package view;

import model.User;
import controller.LibraryController;
import model.Library;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final User systemUser;
    private final JTextField userField;
    private final JPasswordField passField;

    public LoginFrame() {
        systemUser = new User(); 

        setTitle("Media Vault - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 15));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        centerPanel.add(new JLabel("Username:"));
        userField = new JTextField();
        centerPanel.add(userField);

        centerPanel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        centerPanel.add(passField);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 20, 30));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegister);

        // Quick-load button for testing
        JButton btnSample = new JButton("Load Sample User Details");
        
        bottomPanel.add(buttonPanel);
        bottomPanel.add(btnSample);
        
        add(bottomPanel, BorderLayout.SOUTH);

        // --- BUTTON ACTIONS ---

        btnSample.addActionListener(e -> {
            systemUser.register("john", "j@j.com", "123");
            userField.setText("john");
            passField.setText("123");
        });

        btnRegister.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());
            String usernameToRegister = u.isEmpty() ? null : u; 
            
            systemUser.register(usernameToRegister, "user@domain.com", p);
            
            if (usernameToRegister == null || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalid registration details.\nCheck console for details.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration simulated successfully! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnLogin.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());

            if (systemUser.login(u, p)) {
                this.dispose(); 
                
                // Launch Main Application
                Library library = new Library();
                LibraryController controller = new LibraryController(library);
                MainFrame mainFrame = new MainFrame(controller);
                mainFrame.setVisible(true);
            } else {
                if (systemUser.getUsername() == null) {
                    JOptionPane.showMessageDialog(this, "User has not registered yet.\nCheck console for details.", "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect credentials.\nCheck console for details.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
