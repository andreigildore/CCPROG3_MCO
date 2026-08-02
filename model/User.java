package model;

public class User {
    private String username;
    private String email;
    private String password;
    private Library library; // The user now owns their library

    public User() {
        this.library = new Library(); // Initializes an empty library for the user
    }

    public boolean register(String username, String email, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Invalid registration details.");
            return false;
        }
        
        this.username = username;
        this.email = email;
        this.password = password;
        // Sets fields appropriately.
        return true;
    }

    public boolean login(String username, String password) {
        if (this.username == null) {
            System.out.println("User has not registered yet.");
            return false;
        }
        
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login Successful!");
            return true;
        } 
        
        System.out.println("Incorrect credentials.");
        return false;
    }
    
    public String getUsername() {
        return this.username;
    }

    public Library getLibrary() {
        return this.library;
    }
}
