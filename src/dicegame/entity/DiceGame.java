package dicegame.entity;

import player.entity.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DiceGame {


    private Long id;
    private List<Player> players;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int round;
    private Player winner;

    public DiceGame(Long id,List<Player> players) {
        this.id = id;
        this.players = players;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
        this.winner = null;
    }

    public Long getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getRound() {
        return round;
    }

    public Player getWinner() {
        return winner;
    }
}
