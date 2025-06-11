package battlereport.service;

import battlereport.entity.BattleReport;
import dicegame.entity.DiceGame;

public interface BattleReportService {

    boolean createBattleReport(Long userId, DiceGame diceGame);

    BattleReport getBattleReportById(Long userId);


}
