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
    private int currentRound;
    private Player currentPlayer;
    private Player winner;
    boolean gameOver;

    public DiceGame(Long id,List<Player> players) {
        this.id = id;
        this.players = players;
        this.startTime = LocalDateTime.now();
        this.endTime = LocalDateTime.now();
        this.winner = null;
        this.currentRound = 1;
        this.currentPlayer = null;
        this.gameOver = false;
    }

    public Long getId() {
        return id;
    }

    public int getCurrentRound() {
        return currentRound;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }


    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
