package player.view;

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
    private final UtilView utilView;
    public PlayerView() {
        this.playerService = PlayerServiceImpl.getInstance();
        this.utilView = UtilView.getInstance();
    }



    public void addPlayer() {

        System.out.println("        Player  생성      ");
        System.out.println();
        System.out.println(" Player1 name :  ");
        String player1 = UtilService.sc.nextLine();
        System.out.println(" Player2 name :  ");
        String player2 = UtilService.sc.nextLine();

        boolean result = playerService.addPlayer(player1, player2);

        if (result) {

        } else{
            System.out.println();
            System.out.println(" Player 생성 실패");
            System.out.println();
            utilView.userPage();
        }


    }




}
