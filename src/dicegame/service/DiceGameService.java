package dicegame.service;

import dicegame.entity.DiceGame;
import player.entity.Player;

import java.util.List;


public interface DiceGameService {

    boolean createDiceGame(List<Player> players);
    DiceGame getDiceGame(Long id);
    boolean processSkillDice(int diceNumber, Player currentPlayer, Player player1, Player player2);
    boolean processTurn(int round, Player currentPlayer, Player opponent, int diceNumber, int currentRound);
    boolean determineWinner(Player player1, Player player2, DiceGame game);
    boolean finalizeGame(Player player1, Player player2, DiceGame game);
}
