package player.service;

import player.entity.Player;

import java.util.List;

public interface PlayerService {

    boolean addPlayer(String playerName1, String playerName2);

    List<Player> getPlayers();


}
