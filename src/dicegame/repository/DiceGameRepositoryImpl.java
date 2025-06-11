package dicegame.repository;

import dicegame.entity.DiceGame;
import dicegame.service.DiceGameServiceImpl;
import player.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceGameRepositoryImpl implements DiceGameRepository {

    private static Long diceGameID;


    public static DiceGameRepositoryImpl instance;
    public static DiceGameRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceGameRepositoryImpl();
        }
        return instance;
    }

    private final Map<Long, DiceGame> diceGames = new HashMap<>();


    @Override
    public boolean save(List<Player> players){
        try {
            Long id = ++diceGameID;
            diceGames.put(id, new DiceGame(id, players));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public DiceGame getDiceGame(Long id) {
        return diceGames.get(id);
    }
}
