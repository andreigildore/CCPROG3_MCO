public class User {
    private String username;
    private String email;
    private String password;
    private Library library;

    public String getEmail() {
        return email;
    }

    private void setEmail(String input) {
        email = input;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String input) {
        username = input;
    }

    private void setPassword(String input) {
        password = input;
    }

    public void register(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
