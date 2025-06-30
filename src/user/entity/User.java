package user.entity;

import dicegame.entity.DiceGame;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private String loginId;
    private String password;
    private List<DiceGame> diceGames;
    private DiceGame currentDiceGame;

    public User(Long id, String name, String loginId, String password) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.diceGames = new ArrayList<>();
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


    public DiceGame getCurrentDiceGame() {
        return currentDiceGame;
    }


    public String getPassword() {
        return password;
    }

    public List<DiceGame> getDiceGames() {
        return diceGames;
    }

    public void setDiceGames(List<DiceGame> diceGames) {
        this.diceGames = diceGames;
    }

    public void setCurrentDiceGame(DiceGame currentDiceGame) {
        this.currentDiceGame = currentDiceGame;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
//                ", diceGames=" + diceGames +
                ", currentDiceGame=" + currentDiceGame +
                '}';
    }
}
