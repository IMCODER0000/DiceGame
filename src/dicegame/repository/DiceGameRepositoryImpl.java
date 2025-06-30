package dicegame.repository;

import dicegame.entity.DiceGame;
import dicegame.service.DiceGameServiceImpl;
import player.entity.Player;
import player.service.PlayerService;
import player.service.PlayerServiceImpl;
import user.entity.User;
import util.token.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceGameRepositoryImpl implements DiceGameRepository {

    private static Long diceGameID = 0L;


    public static DiceGameRepositoryImpl instance;
    public static DiceGameRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DiceGameRepositoryImpl();
        }
        return instance;
    }

    private final Map<Long, DiceGame> diceGames = new HashMap<>();
    private final PlayerService playerService;

    public DiceGameRepositoryImpl() {
        playerService = PlayerServiceImpl.getInstance();
    }



    @Override
    public boolean save(List<Player> players){
        try {
            Long min = 0L;
            Token token = Token.getInstance();
            Long id = ++diceGameID;
            DiceGame newDiceGame = new DiceGame(id, players);
            for (Player player : players) {
                min = Long.min(player.getId(), min);
            }
            newDiceGame.setCurrentPlayer(playerService.getPlayer(min));
            diceGames.put(id, newDiceGame);
            User user = Token.getInstance().getUser();
            user.setCurrentDiceGame(newDiceGame);

            List<DiceGame> userDiceGames = user.getDiceGames();
            userDiceGames.add(newDiceGame);
            user.setDiceGames(userDiceGames);
            user.setCurrentDiceGame(newDiceGame);
            token.setUser(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public DiceGame getDiceGame(Long id) {
        return diceGames.get(id);
    }
}
