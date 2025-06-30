package player.repository;

import player.entity.Player;

public interface PlayerRepository {

    boolean save(Player player1, Player player2);;

    Player getPlayer(Long playerId);


}
