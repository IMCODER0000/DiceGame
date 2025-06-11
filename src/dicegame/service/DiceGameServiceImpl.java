package dicegame.service;

import dicegame.entity.DiceGame;
import dicegame.repository.DiceGameRepositoryImpl;
import player.entity.Player;

import java.util.List;

public class DiceGameServiceImpl implements DiceGameService {

    public static DiceGameServiceImpl instance;
    public static DiceGameServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceGameServiceImpl();
        }
        return instance;
    }

    private final DiceGameRepositoryImpl diceGameRepository;

    private DiceGameServiceImpl() {
        diceGameRepository = DiceGameRepositoryImpl.getInstance();
    }


    @Override
    public boolean createDiceGame(List<Player> players) {
        return diceGameRepository.save(players);
    }

    @Override
    public DiceGame getDiceGame(Long id) {
        return diceGameRepository.getDiceGame(id);
    }

}
