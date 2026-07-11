/**
 * Represents a user account with login credentials and a media library.
 */
public class User {
    private String username;
    private String email;
    private String password;
    private Library library;

    /**
     * Creates a new user with an empty library.
     */
    public User () {
        this.library = new Library();
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Library getLibrary() {
        return library;
    }

    private void setEmail(String input) {
        email = input;
    }

    private void setUsername(String input) {
        username = input;
    }

    private void setPassword(String input) {
        password = input;
    }

    /**
     * Registers the user with the given credentials.
     * 
     * @param username the chosen username
     * @param email the user's email address
     * @param password the chosen password
     * Pre-condition: ALl parameters must be non-null and not blank, they must also reflect the user's valid details.
     * Post-condition: The user's fields are set to the given parameters.
     */
    public void register(String username, String email, String password) {
        // Reject if any field is null or blank.
        if (username == null || username.isBlank() || email == null || email.isBlank()
           || password == null || password.isBlank()) {
            System.out.println("Invalid registration details.");
        }
        else {
            setUsername(username);
            setEmail(email);
            setPassword(password);
            System.out.printf("Successfully Registered with username %s!\n", this.username);
        }
        
    }

    /**
     * Logs in the user given the password and the username.
     * 
     * @param username the username to check
     * @param password the password to check
     * @return true if credentials match the stored ones, false otherwise
     * Pre-condition: The user must be registered (username not null/blank).
     * Post-condition: Returns the result of the login (logs in = true/ does not log in = false).
     */
    public boolean login(String username, String password) {
        if (this.username == null || this.username.isBlank()) { // Only checks one since user details are initialized simultaneously.
            System.out.println("User has not registered yet.");
            return false;
        } 
        else {
            // Match input credentials against stored values.
            if (this.username.equals(username) && this.password.equals(password)) {
                System.out.println("Login Successful!");
                return true;
            }
            else {
                System.out.println("Invalid credentials.");
                return false;
            }
        }
    }
}
