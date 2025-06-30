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

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }



    public int getTotalScore() {
        return totalScore;
    }

    public boolean isCanSkill() {
        return canSkill;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setCanSkill(boolean canSkill) {
        this.canSkill = canSkill;
    }


}
