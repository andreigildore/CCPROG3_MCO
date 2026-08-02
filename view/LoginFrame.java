package view;

import controller.UserController;
import controller.LibraryController;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final UserController userController;
    private final JTextField userField;
    private final JPasswordField passField;

    public LoginFrame() {
        userController = new UserController(); 

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
            userController.register("john", "j@j.com", "123");
            userField.setText("john");
            passField.setText("123");
        });

        btnRegister.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());
            String usernameToRegister = u.isEmpty() ? null : u; 
            
            boolean success = userController.register(usernameToRegister, "user@domain.com", p);
            
            if (!success) {
                JOptionPane.showMessageDialog(this, "Invalid registration details.\nCheck console for details.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Registration simulated successfully! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnLogin.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());

            if (userController.login(u, p)) {
                this.dispose(); 
                
                // Launch Main Application using the User's specific library AND username
                String loggedInUser = userController.getUser().getUsername();
                LibraryController libraryController = new LibraryController(userController.getLibrary(), loggedInUser);
                MainFrame mainFrame = new MainFrame(libraryController);
                mainFrame.setVisible(true);
            } else {
                if (userController.getUser().getUsername() == null) {
                    JOptionPane.showMessageDialog(this, "User has not registered yet.\nCheck console for details.", "Login Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect credentials.\nCheck console for details.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
