package dicegame.service;

import dicegame.entity.DiceGame;
import player.entity.Player;

import java.util.List;

public interface DiceGameService {

    boolean createDiceGame(List<Player> players);

    DiceGame getDiceGame(Long id);



}
