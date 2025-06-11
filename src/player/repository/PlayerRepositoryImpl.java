package player.repository;

import player.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerRepositoryImpl implements PlayerRepository {

    public static PlayerRepositoryImpl instance;
    public static PlayerRepositoryImpl getInstance(){
        if(instance == null){
            instance = new PlayerRepositoryImpl();
        }
        return instance;
    }

    private final Map<Long, Player> players = new HashMap<>();


    @Override
    public boolean save(String playerName1, String playerName2) {

        try {
            Player player1 = new Player(1L, playerName1);
            Player player2 = new Player(1L, playerName1);
            players.put(player1.getId(), player1);
            players.put(player2.getId(), player2);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }



    }

    @Override
    public Player getPlayer(Long playerId) {
        players.get(playerId);
        return null;
    }
}
