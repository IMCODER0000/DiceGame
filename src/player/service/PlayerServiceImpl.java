package player.service;

import dicegame.service.DiceGameService;
import dicegame.service.DiceGameServiceImpl;
import player.entity.Player;
import player.repository.PlayerRepository;
import player.repository.PlayerRepositoryImpl;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private static Long playerId = 0L;


    public static PlayerServiceImpl instance;

    public static PlayerServiceImpl getInstance() {
        if (instance == null) {
            instance = new PlayerServiceImpl();
        }
        return instance;
    }

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl() {
        this.playerRepository = PlayerRepositoryImpl.getInstance();
    }
    public DiceGameService getDiceGameService() {
        return DiceGameServiceImpl.getInstance();
    }


    @Override
    public boolean addPlayer(String playerName1, String playerName2) {
        Player player1 = new Player(++playerId, playerName1);
        Player player2 = new Player(++playerId, playerName2);
        if(!getDiceGameService().createDiceGame(List.of(player1, player2))){
            System.out.println("게임 만들기 실패");
            return false;
        }
        return playerRepository.save(player1, player2);
    }

    @Override
    public List<Player> getPlayers() {
        Player player1 = playerRepository.getPlayer(1L);
        Player player2 = playerRepository.getPlayer(2L);
        return List.of(player1, player2);
    }

    @Override
    public Player getPlayer(Long playerId) {
        return playerRepository.getPlayer(playerId);
    }

}
