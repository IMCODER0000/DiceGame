package battlereport.service;

import battlereport.entity.BattleReport;
import dicegame.entity.DiceGame;

import java.util.List;

public interface BattleReportService {

    boolean createBattleReport(Long userId, DiceGame diceGame);

    List<BattleReport> getBattleReportById(Long userId);
    BattleReport getBattleReportByDiceGame(DiceGame diceGame);


}
