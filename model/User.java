package model;

import java.io.*;
import java.util.HashMap;

public class User {
    private String username;
    private String email;
    private String password;
    private Library library; 

    // Shared database across all instances to act like a real website backend
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private static final String DB_FILE = "registered_users.txt";

    public User() {
        this.library = new Library();
        loadDatabase();
    }

    @SuppressWarnings("unchecked")
    private void loadDatabase() {
        File file = new File(DB_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                userDatabase = (HashMap<String, String>) ois.readObject();
            } catch (Exception e) {
                System.err.println("Warning: Could not load user database.");
            }
        }
    }

    private void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DB_FILE))) {
            oos.writeObject(userDatabase);
        } catch (Exception e) {
            System.err.println("Error saving user database.");
        }
    }

    public boolean register(String username, String email, String password) {
        // Test Cases: Null/Missing details
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Invalid registration details.");
            return false;
        }
        
        this.username = username;
        this.email = email;
        this.password = password;
        
        // Store case-sensitive credentials and permanently save to file
        userDatabase.put(username, password);
        saveDatabase();
        
        return true;
    }

    public boolean login(String username, String password) {
        // Test Case: Unregistered login
        if (username == null || !userDatabase.containsKey(username)) {
            System.out.println("User has not registered yet.");
            return false;
        }
        
        String storedPassword = userDatabase.get(username);
        
        // Test Case: Successful login (Strictly case-sensitive string match)
        if (storedPassword.equals(password)) {
            this.username = username;
            this.password = storedPassword;
            System.out.println("Login Successful!");
            return true;
        } 
        
        // Test Case: Incorrect password
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
