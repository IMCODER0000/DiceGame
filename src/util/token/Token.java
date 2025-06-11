package util.token;

import user.entity.User;

public class Token {

    private static Token instance;
    private User user;

    private Token() {}

    public static Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

