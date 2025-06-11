package player.repository;

import player.entity.Player;

public interface PlayerRepository {

    boolean save(String playerName1, String playerName2);

    Player getPlayer(Long playerId);


}
