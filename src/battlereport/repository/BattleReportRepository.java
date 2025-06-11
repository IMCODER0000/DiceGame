package battlereport.repository;

import battlereport.entity.BattleReport;
import dicegame.entity.DiceGame;

public interface BattleReportRepository {


    boolean save(Long userId, DiceGame diceGame);

    BattleReport getBattleReportById(Long userId);


}
