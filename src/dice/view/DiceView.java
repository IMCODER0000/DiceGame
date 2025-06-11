package dice.view;

import java.util.Arrays;

public class DiceView {

    public static DiceView instance;
    public static DiceView getInstance() {
        if (instance == null) {
            instance = new DiceView();
        }
        return instance;
    }



    public static String[] diceArt(int num) {
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

    public void diceStart() throws InterruptedException {
        int[] diceNumbers = {1, 2, 3};
        String[][] arts = new String[diceNumbers.length][];

        for (int i = 0; i < diceNumbers.length; i++) {
            arts[i] = diceArt(diceNumbers[i]);
        }

        int diceCount = diceNumbers.length;
        int lineCount = arts[0].length;

        for (int d = 0; d < diceCount; d++) {
            for (int line = 0; line < lineCount; line++) {
                System.out.println(arts[d][line]);
            }
            System.out.println();
            Thread.sleep(1000);
        }
    }





}
