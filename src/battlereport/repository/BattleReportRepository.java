package battlereport.repository;

import battlereport.entity.BattleReport;
import dicegame.entity.DiceGame;

import java.util.List;

public interface BattleReportRepository {


    boolean save(Long userId, DiceGame diceGame);

    List<BattleReport> getBattleReportById(Long userId);

    BattleReport getBattleReportByDiceGame(DiceGame diceGame);

}
