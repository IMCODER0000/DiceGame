package dicegame.repository;

import dicegame.entity.DiceGame;
import player.entity.Player;

import java.util.List;

public interface DiceGameRepository {

    boolean save(List<Player> players);

    DiceGame getDiceGame(Long id);




}
