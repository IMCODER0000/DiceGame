package battlereport.repository;


import battlereport.entity.BattleReport;
import dicegame.entity.DiceGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BattleReportRepositoryImpl implements BattleReportRepository {

    private static Long battleReportId = 0L;

    public static BattleReportRepositoryImpl instance;
    public static BattleReportRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new BattleReportRepositoryImpl();
        }
        return instance;
    }

    private final Map<Long, BattleReport> battleReports = new HashMap<>();


    @Override
    public boolean save(Long userId, DiceGame diceGame) {
        try {
            Long id = ++battleReportId;
            battleReports.put(id, new BattleReport(id, userId, diceGame));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public List<BattleReport> getBattleReportById(Long userId) {
        return battleReports.values().stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public BattleReport getBattleReportByDiceGame(DiceGame diceGame) {
        return battleReports.values().stream()
                .filter(b -> b.getDiceGame().equals(diceGame))
                .findFirst().orElse(null);
    }
}
