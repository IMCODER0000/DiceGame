package user.entity;

public class User {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private int currentRound;

    public User(Long id, String name, String loginId, String password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.currentRound = 0;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getCurrentRound() {
        return currentRound;
    }
}
