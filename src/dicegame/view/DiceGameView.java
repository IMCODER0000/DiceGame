package dicegame.view;

import dicegame.service.DiceGameService;
import dicegame.service.DiceGameServiceImpl;
import player.view.PlayerView;
import util.service.UtilService;
import util.view.UtilView;

public class DiceGameView {

    public static DiceGameView instance;

    public static DiceGameView getInstance() {
        if (instance == null) {
            instance = new DiceGameView();
        }
        return instance;
    }

    private final PlayerView playerView;
    private final DiceGameService diceGameService;

    private DiceGameView() {
        this.playerView = PlayerView.getInstance();
        this.diceGameService = DiceGameServiceImpl.getInstance();
    }

    public void createStartDiceGameView() {
        System.out.println("       Dice Game 준비       ");
        System.out.println();
        System.out.println();
        playerView.addPlayer();

    }

    public void DiceGame() {
        System.out.println("       Dice Game을 시작 합니다      ");
        System.out.println();
        System.out.println("        라운드 " + diceGameService.getDiceGame(1L));
        System.out.println();
        System.out.println("+-----------------------------+");
        System.out.println("|             게임시작           |");
        System.out.println("|                              |");
        System.out.println("|          Start [Enter]       |");
        System.out.println("|                              |");
        System.out.println("+-----------------------------+");



    }




}
