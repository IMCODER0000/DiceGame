package dicegame.service;

import dicegame.entity.DiceGame;
import dicegame.repository.DiceGameRepository;
import dicegame.repository.DiceGameRepositoryImpl;
import player.entity.Player;
import user.entity.User;
import user.service.UserService;
import user.service.UserServiceImpl;
import util.token.Token;

import java.time.LocalDateTime;
import java.util.List;


public class DiceGameServiceImpl implements DiceGameService {
    private static final int SKILL_DICE_STEAL = 3;
    private static final int SKILL_DICE_SUICIDE = 4;
    private static final int DEATH_SCORE = -99999999;
    
    private static DiceGameServiceImpl instance;
    private final DiceGameRepository diceGameRepository;
    private final UserService userService;

    private DiceGameServiceImpl() {
        this.diceGameRepository = DiceGameRepositoryImpl.getInstance();
        this.userService = UserServiceImpl.getInstance();
    }

    public static synchronized DiceGameServiceImpl getInstance() {
        if (instance == null) {
            instance = new DiceGameServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean createDiceGame(List<Player> players) {
        if (players == null || players.size() != 2) {
            return false;
        }
        return diceGameRepository.save(players);
    }

    @Override
    public DiceGame getDiceGame(Long id) {
        if (id == null) {
            return null;
        }
        return diceGameRepository.getDiceGame(id);
    }

    @Override
    public boolean processSkillDice(int diceNumber, Player currentPlayer, Player player1, Player player2) {
        try {
            if (currentPlayer == null || player1 == null || player2 == null) {
                return false;
            }

            boolean isPlayer1 = currentPlayer.equals(player1);
            Player opponent = isPlayer1 ? player2 : player1;

            switch (diceNumber) {
                case SKILL_DICE_STEAL:
                    currentPlayer.setTotalScore(currentPlayer.getTotalScore() + opponent.getTotalScore());
                    opponent.setTotalScore(0);
                    break;
                    
                case SKILL_DICE_SUICIDE:
                    currentPlayer.setTotalScore(DEATH_SCORE);
                    opponent.setTotalScore(0);
                    break;
                    
                default:
                    currentPlayer.setTotalScore(currentPlayer.getTotalScore() + diceNumber);
                    break;
            }
            return true;
            
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public boolean processTurn(int round, Player currentPlayer, Player opponent, int diceNumber, int currentRound) {
        try {
            if (currentPlayer == null || opponent == null || diceNumber < 1 || diceNumber > 6) {
                return false;
            }

            if (currentRound == 1 && round == 0) {
                currentPlayer.setCanSkill(diceNumber % 2 == 0);
            }

            currentPlayer.setTotalScore(currentPlayer.getTotalScore() + diceNumber);
            currentPlayer.setScore(diceNumber);

            Token token = Token.getInstance();
            if (token.getUser() != null && token.getUser().getCurrentDiceGame() != null) {
                token.getUser().getCurrentDiceGame().setCurrentRound(currentRound + 1);
                token.getUser().getCurrentDiceGame().setCurrentPlayer(opponent);
            }

            return true;
            
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean determineWinner(Player player1, Player player2, DiceGame game) {
        try {
            if (player1 == null || player2 == null || game == null) {
                return false;
            }

            if (player1.getTotalScore() > player2.getTotalScore()) {
                game.setWinner(player1);
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                game.setWinner(player2);
            } else {
                game.setWinner(null);
            }
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean finalizeGame(Player player1, Player player2, DiceGame game) {
        try {
            if (player1 == null || player2 == null || game == null) {
                return false;
            }

            game.setPlayers(List.of(player1, player2));
            game.setEndTime(LocalDateTime.now());
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
}
