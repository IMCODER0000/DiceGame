package battlereport.service;

import battlereport.entity.BattleReport;
import battlereport.repository.BattleReportRepository;
import battlereport.repository.BattleReportRepositoryImpl;
import dicegame.entity.DiceGame;
import dicegame.repository.DiceGameRepositoryImpl;

import java.util.List;

public class BattleReportServiceImpl implements BattleReportService {

    public static BattleReportServiceImpl instance;
    public static BattleReportServiceImpl getInstance() {
        if (instance == null) {
            instance = new BattleReportServiceImpl();
        }
        return instance;
    }

    private final BattleReportRepository battleReportRepository;
    public BattleReportServiceImpl() {
        battleReportRepository = BattleReportRepositoryImpl.getInstance();
    }



    @Override
    public boolean createBattleReport(Long userId, DiceGame diceGame) {
        return battleReportRepository.save(userId,diceGame);
    }

    @Override
    public List<BattleReport> getBattleReportById(Long userId) {
        return battleReportRepository.getBattleReportById(userId);
    }

    @Override
    public BattleReport getBattleReportByDiceGame(DiceGame diceGame) {
        return battleReportRepository.getBattleReportByDiceGame(diceGame);
    }
}
