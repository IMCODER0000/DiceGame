package dice.service;

import user.dto.DiceDTO;

public interface DiceService {

    DiceDTO rollDice();
    String[] diceArt(int num);

}
