package user.dto;

public class DiceDTO {

    private int diceNumber;
    private String[] diceArt;

    public DiceDTO(int diceNumber, String[] diceArt) {
        this.diceNumber = diceNumber;
        this.diceArt = diceArt;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public String[] getDiceArt() {
        return diceArt;
    }
}
