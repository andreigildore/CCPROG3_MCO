public class User {
    private String username;
    private String email;
    private String password;
    private Library library;

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

    public void register(String username, String email, String password) {
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

    public boolean login(String username, String password) {
        if (this.username == null || this.username.isBlank()) { // only checks one since user details are initialized simultaneously
            System.out.println("User has not registered yet.");
            return false;
        } 
        else {
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
