package player.view;

import dicegame.service.DiceGameService;
import dicegame.service.DiceGameServiceImpl;
import dicegame.view.DiceGameView;
import player.service.PlayerService;
import player.service.PlayerServiceImpl;
import util.service.UtilService;
import util.view.UtilView;

public class PlayerView {

    public static PlayerView instance;

    public static PlayerView getInstance() {
        if (instance == null) {
            instance = new PlayerView();
        }
        return instance;
    }

    private final PlayerService playerService;
    private final DiceGameService diceGameService;
    public PlayerView() {
        this.playerService = PlayerServiceImpl.getInstance();
        this.diceGameService = DiceGameServiceImpl.getInstance();
    }

    private UtilView getUtilView() {
        return UtilView.getInstance();
    }
    private DiceGameView getDiceGameView() {
        return DiceGameView.getInstance();
    }



    public void addPlayer() throws InterruptedException {

        UtilService.sc.nextLine();
        System.out.println("        Player  생성      ");
        System.out.println();
        System.out.print(" Player1 name :  ");
        String player1 = UtilService.sc.nextLine();
        System.out.print(" Player2 name :  ");
        String player2 = UtilService.sc.nextLine();

        boolean result = playerService.addPlayer(player1, player2);

        if (result) {
            getDiceGameView().startDiceGame
                    ();
        } else{
            System.out.println();
            System.out.println(" Player 생성 실패");
            System.out.println();
            getUtilView().userPage();
        }


    }




}
