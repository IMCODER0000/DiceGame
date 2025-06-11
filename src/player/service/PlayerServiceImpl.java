package player.service;

import player.entity.Player;
import player.repository.PlayerRepository;
import player.repository.PlayerRepositoryImpl;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {

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


    @Override
    public boolean addPlayer(String playerName1, String playerName2) {
        return playerRepository.save(playerName1, playerName2);
    }

    @Override
    public List<Player> getPlayers() {
        Player player1 = playerRepository.getPlayer(1L);
        Player player2 = playerRepository.getPlayer(2L);
        return List.of(player1, player2);
    }

}
