package battlereport.entity;

import dicegame.entity.DiceGame;

import java.util.List;

public class BattleReport {

    private Long id;
    private Long userId;
    private DiceGame diceGame;


    public BattleReport(Long id, Long userId, DiceGame diceGame) {
        this.id = id;
        this.userId = userId;
        this.diceGame = diceGame;
    }
}
