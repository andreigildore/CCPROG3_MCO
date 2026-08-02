package controller;

import model.*;

/**
 * Handles user-related operations such as registration and login.
 */
public class UserController {
    private User user;

    public UserController() {
        user = new User();
    }

    public boolean register(String username, String email, String password) {
        return user.register(username, email, password);
    }

    public boolean login(String username, String password) {
        return user.login(username, password);
    }

    public User getUser() {
        return user;
    }

    public Library getLibrary() {
        return user.getLibrary();
    }
}
