package util.view;

import battlereport.entity.BattleReport;
import battlereport.view.BattleReportView;
import dice.view.DiceView;
import dicegame.view.DiceGameView;
import player.view.PlayerView;
import user.view.UserView;
import util.service.UtilService;
import util.token.Token;

public class UtilView {

    public static UtilView instance = new UtilView();

    Token token = Token.getInstance();

    public static UtilView getInstance() {
        if (instance == null) {
            instance = new UtilView();
        }
        return instance;
    }

    private final UserView  userView;
    private final DiceGameView diceGameView;
    private final BattleReportView battleReportView;
    private final PlayerView playerView;

    private UtilView(){
        this.userView = UserView.getInstance();
        this.diceGameView = DiceGameView.getInstance();
        this.battleReportView = BattleReportView.getInstance();
        this.playerView = PlayerView.getInstance();
    }



    public void landingPage() throws InterruptedException {

        System.out.println("      Dice 게임에 오신걸 환영 합니다.    ");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 회원가입 ");
        System.out.println(" 2. 로그인 ");
        System.out.println(" 3. 종료 ");
        System.out.print(" 입력 : ");
        int input = UtilService.sc.nextInt();

        if (input == 1) {
            userView.register();
        } else if (input == 2) {
            userView.login();
        } else if(input == 3) {
            System.exit(0);
        } else{

        }

    }

    public void userPage() throws InterruptedException {

        System.out.println("     " + token.getUser().getName() + "님  Dice 게임에 오신걸 환영 합니다.    ");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 게임 시작 ");
        System.out.println(" 2. 로그아웃 ");
        System.out.println(" 3. 배틀레포트 ");
        System.out.println(" 4. 종료 ");
        System.out.print(" 입력 : ");
        int input = UtilService.sc.nextInt();
        if (input == 1) {
            diceGameView.DiceGameReady();
        } else if(input == 2) {
            token.tokenClear();
            System.out.println();
            System.out.println("        로그아웃 되었습니다.        ");
            System.out.println();
            landingPage();
        } else if (input == 3) {
            battleReportView.battleReportByUserId();
        } else if(input == 4) {
            System.exit(0);
        } else{
            System.out.println("잘못된 숫자를 입력 하셨습니다 .");
            userPage();
        }



    }




}
