package dice.view;

import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import user.dto.DiceDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DiceView {


    public static DiceView instance;
    public static DiceView getInstance() {
        if (instance == null) {
            instance = new DiceView();
        }
        return instance;
    }
    private final DiceService diceService;
    private DiceView() {
        this.diceService = new DiceServiceImpl();
    }





    public void diceStart() throws InterruptedException {
        int[] diceNumbers = {1, 2, 3};
        String[][] arts = new String[diceNumbers.length][];

        for (int i = 0; i < diceNumbers.length; i++) {
            arts[i] = diceService.diceArt(diceNumbers[i]);
        }

        int diceCount = diceNumbers.length;
        int lineCount = arts[0].length;

        for (int d = 0; d < diceCount; d++) {
            for (int line = 0; line < lineCount; line++) {
                System.out.println(arts[d][line]);
            }
            System.out.println();
            Thread.sleep(400);
        }
    }







}
