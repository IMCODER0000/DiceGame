package battlereport.view;

import battlereport.entity.BattleReport;
import battlereport.service.BattleReportService;
import battlereport.service.BattleReportServiceImpl;
import dicegame.entity.DiceGame;
import player.entity.Player;
import util.service.UtilService;
import util.token.Token;
import util.view.UtilView;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class BattleReportView {

    public static BattleReportView instance;
    public static BattleReportView getInstance(){
        if(instance==null){
            instance=new BattleReportView();
        }
        return instance;
    }

    private final BattleReportService battleReportService;
    public BattleReportView(){
        this.battleReportService=BattleReportServiceImpl.getInstance();
    }

    public UtilView getUtilView(){
        return UtilView.getInstance();
    }


    public void battleReport() throws InterruptedException {
        DiceGame diceGame = Token.getInstance().getUser().getCurrentDiceGame();
        Duration duration = Duration.between(diceGame.getStartTime(), diceGame.getEndTime());
        long seconds = duration.getSeconds();
        List<Player> players = diceGame.getPlayers();
        String winner = Optional.ofNullable(diceGame.getWinner())
                .map(Player::getPlayerName)
                .orElse("무승부");



        System.out.println("\n===============================================");
        System.out.println("⚔️   ==========  BATTLE REPORT  ==========   ⚔️");
        System.out.println("===============================================\n");

        System.out.printf("⏱️  플레이 시간 : %d초\n", seconds);
        System.out.printf("🏆  승리자      : %s\n\n", winner);

        System.out.println("📊  최종 점수");
        System.out.println("-----------------------------------------------");
        for (Player player : players) {
            System.out.printf("👤  %-10s : %3d 점\n", player.getPlayerName(), player.getTotalScore());
        }
        System.out.println("-----------------------------------------------\n");
        System.out.println();
        System.out.println("                    Home [Enter]                ");
        String input = UtilService.sc.nextLine();
        System.out.println();

        if (input.isEmpty()) {
            getUtilView().userPage();
        } else{
            System.out.println();
            System.out.println("        오  류         ");
            System.out.println();
            getUtilView().userPage();
        }



    }

    public void battleReportByUserId() throws InterruptedException {
        List<BattleReport> battleReportById = battleReportService.getBattleReportById(Token.getInstance().getUser().getId());

        System.out.println("\n=============================================");
        System.out.println(battleReportById.size());
        System.out.println("\n=============================================");

        if(battleReportById.size()==0){
            System.out.println();
            System.out.println("============================================");
            System.out.println("                  비어 있음                   ");
            System.out.println("============================================");
            System.out.println("                Home [Enter]                ");
            System.out.println("============================================");
            String input = UtilService.sc.nextLine();
            System.out.println();
            System.out.println();
            System.out.println();
            UtilService.sc.nextLine();

            if (input.isEmpty()) {
                getUtilView().userPage();
            } else{
                System.out.println();
                System.out.println("        오  류         ");
                System.out.println();
                getUtilView().userPage();
            }

        } else{
            for (BattleReport battleReport : battleReportById) {

                DiceGame diceGame = battleReport.getDiceGame();
                Duration duration = Duration.between(diceGame.getStartTime(), diceGame.getEndTime());
                long seconds = duration.getSeconds();
                List<Player> players = diceGame.getPlayers();
                String winner = Optional.ofNullable(diceGame.getWinner())
                        .map(Player::getPlayerName)
                        .orElse("무승부");

                System.out.println("\n===============================================");
                System.out.println("⚔️   ==========  BATTLE REPORT  ==========   ⚔️");
                System.out.println("===============================================\n");

                System.out.printf("⏱️  플레이 시간 : %d초\n", seconds);
                System.out.printf("🏆  승리자      : %s\n\n", winner);

                System.out.println("📊  최종 점수");
                System.out.println("-----------------------------------------------");
                for (Player player : players) {
                    System.out.printf("👤  %-10s : %3d 점\n", player.getPlayerName(), player.getTotalScore());
                }

                System.out.println();
                System.out.println();

            }
            System.out.println("-----------------------------------------------\n");
            System.out.println("============================================");
            System.out.println("                Home [Enter]                ");
            System.out.println("============================================");
            String input = UtilService.sc.nextLine();
            if (input.isEmpty()) {
                getUtilView().userPage();
            } else{
                System.out.println();
                System.out.println("        오  류         ");
                System.out.println();
                getUtilView().userPage();
            }
        }




    }



}
