package player.entity;

public class Player {

    private Long id;
    private String playerName;
    private int score;
    private int totalScore;
    private boolean canSkill;

    public Player(Long id, String playerName) {
        this.playerName = playerName;
        this.id = id;
        this.score = 0;
        this.totalScore = 0;
        this.canSkill = false;
    }


    public Long getId() {
        return id;
    }
}
