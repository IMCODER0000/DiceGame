package dice.service;

import user.dto.DiceDTO;

import java.util.Random;


public class DiceServiceImpl implements DiceService {
    Random rand = new Random();
    public static DiceService instance;
    public static DiceService getInstance() {
        if(instance == null) {
            instance = new DiceServiceImpl();
        }
        return instance;
    }


    public DiceDTO rollDice() {
        int dice = rand.nextInt(6) + 1;

        DiceDTO diceDTO = new DiceDTO(dice, diceArt(dice));

        return diceDTO;


    }

    public String[] diceArt(int num) {
        switch (num) {
            case 1:
                return new String[]{
                        "+-----------+",
                        "|           |",
                        "|     ●     |",
                        "|           |",
                        "+-----------+"
                };
            case 2:
                return new String[]{
                        "+-----------+",
                        "| ●         |",
                        "|           |",
                        "|         ● |",
                        "+-----------+"
                };
            case 3:
                return new String[]{
                        "+-----------+",
                        "| ●         |",
                        "|     ●     |",
                        "|         ● |",
                        "+-----------+"
                };
            case 4:
                return new String[]{
                        "+-----------+",
                        "| ●       ● |",
                        "|           |",
                        "| ●       ● |",
                        "+-----------+"
                };
            case 5:
                return new String[]{
                        "+-----------+",
                        "| ●       ● |",
                        "|     ●     |",
                        "| ●       ● |",
                        "+-----------+"
                };
            case 6:
                return new String[]{
                        "+-----------+",
                        "| ●       ● |",
                        "| ●       ● |",
                        "| ●       ● |",
                        "+-----------+"
                };
            default:
                return new String[]{
                        "+-----------+",
                        "|  Invalid  |",
                        "|   Dice    |",
                        "|  Number   |",
                        "+-----------+"
                };
        }
    }


}
