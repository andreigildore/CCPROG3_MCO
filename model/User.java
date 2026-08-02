package model;

public class User {
    private String username;
    private String email;
    private String password;

    public User() {
        // Initializes empty, representing an unregistered state initially
    }

    public void register(String username, String email, String password) {
        // Test Case: Missing password ("") or Null username (null)
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Invalid registration details.");
            return;
        }
        
        // Test Case: Valid registration
        this.username = username;
        this.email = email;
        this.password = password;
        // Test script expectation: "Sets fields appropriately."
    }

    public boolean login(String username, String password) {
        // Test Case: Unregistered login
        if (this.username == null) {
            System.out.println("User has not registered yet.");
            return false;
        }
        
        // Test Case: Successful login
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login Successful!");
            return true;
        } 
        
        // Test Case: Incorrect password
        System.out.println("Incorrect credentials.");
        return false;
    }
    
    // Helper method for the GUI popup logic
    public String getUsername() {
        return this.username;
    }
}
