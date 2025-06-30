package dicegame.view;

import battlereport.entity.BattleReport;
import battlereport.service.BattleReportService;
import battlereport.service.BattleReportServiceImpl;
import battlereport.view.BattleReportView;
import dice.service.DiceService;
import dice.service.DiceServiceImpl;
import dice.view.DiceView;
import dicegame.entity.DiceGame;
import dicegame.service.DiceGameService;
import dicegame.service.DiceGameServiceImpl;
import player.entity.Player;
import player.view.PlayerView;
import user.dto.DiceDTO;
import util.service.UtilService;
import util.token.Token;
import util.view.UtilView;

import java.util.List;

import static util.service.UtilService.waitForEnter;

public class DiceGameView {

    private static final String GAME_BORDER = "+----------------------------------------+";
    private static final String ROUND_FORMAT = " |               ROUND %2d                |";
    private static final String SCORE_FORMAT = "|  %-10s %-3d    vs    %-3d %-10s    |";
    private static final int TOTAL_ROUNDS = 2;
    Token token = Token.getInstance();



    private static DiceGameView instance;
    private final PlayerView playerView;
    private final DiceGameService diceGameService;
    private final DiceView diceView;
    private final BattleReportService battleReportService;
    private final BattleReportView battleReportView;
    private final DiceService diceService;

    private DiceGameView() {
        this.playerView = PlayerView.getInstance();
        this.diceGameService = DiceGameServiceImpl.getInstance();
        this.diceView = DiceView.getInstance();
        this.battleReportService = BattleReportServiceImpl.getInstance();
        this.battleReportView = BattleReportView.getInstance();
        this.diceService = DiceServiceImpl.getInstance();
    }

    public static DiceGameView getInstance() {
        if (instance == null) {
            instance = new DiceGameView();
        }
        return instance;
    }

    public UtilView getUtilView() {
        return UtilView.getInstance();
    }



    public void createStartDiceGameView() throws InterruptedException {
        System.out.println("Dice Game 준비");
        System.out.println("\n");
        playerView.addPlayer();
    }


    public void startDiceGame() throws InterruptedException {
        DiceGame currentDiceGame = token.getUser().getCurrentDiceGame();
        List<Player> players = currentDiceGame.getPlayers();
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        for (int round = 0; round < TOTAL_ROUNDS; round++) {
            int currentRound = round + 1;
            currentDiceGame.setCurrentPlayer(player1);

            displayRoundStart(currentRound, player1, player2);

            // 플레이어 1 턴
            processPlayerTurn(round, player1, player2, currentRound);

            // 플레이어 2 턴
            currentDiceGame.setCurrentPlayer(player2);
            processPlayerTurn(round, player2, player1, currentRound);
        }

        processGameEnd(player1, player2, currentDiceGame);
    }


    private void displayRoundStart(int currentRound, Player player1, Player player2) {
        System.out.println(GAME_BORDER);
        System.out.println("|            🎲  DICE GAME 🎲            |");
        System.out.println(GAME_BORDER);
        System.out.println("|                                        |");
        System.out.printf(ROUND_FORMAT + "\n", currentRound);
        System.out.println("|                                        |");
        System.out.println(GAME_BORDER.replace("--------", "------"));
        displayScoreBoard(player1, player2);

    }

    private void processPlayerTurn(int round, Player currentPlayer, Player opponent, int currentRound) throws InterruptedException {

        System.out.println("\n" + String.format("👉  %s 님 차례입니다.\n", currentPlayer.getPlayerName()));
        System.out.println();
        waitForEnter("주사위 굴리기");
        displayDiceRollAnimation();
        
        DiceDTO diceDTO = diceService.rollDice();
        displayDiceArt(diceDTO);

        if (!diceGameService.processTurn(round, currentPlayer, opponent, diceDTO.getDiceNumber(), currentRound)) {
            handleDiceRollError();
        }

        if (diceDTO.getDiceNumber() % 2 == 0) {
            System.out.println();
            System.out.println( currentRound + "님 스킬 주사위 획득! ");
            System.out.println();
        }


        displayScoreBoard(currentPlayer, opponent);
        System.out.println();

    }


    private void displayDiceRollAnimation() throws InterruptedException {
        System.out.println("\n");
        diceView.diceStart();
        System.out.println("\n");
        System.out.println(" = = = = = = = = = = = = = = = = = = = ");
        System.out.println();
    }


    private void displayDiceArt(DiceDTO diceDTO) {
        for (String line : diceDTO.getDiceArt()) {
            System.out.println(line);
        }
    }


    private void displayScoreBoard(Player p1, Player p2) {
        String skillIndicator = "✨";
        System.out.println();
        System.out.println("════════════════════════════════════");
        System.out.println("            SCORE BOARD            ");
        System.out.println("════════════════════════════════════");

        System.out.printf(" %-10s: %-3d %s%n",
                p1.getPlayerName(),
                p1.getTotalScore(),
                p1.isCanSkill() ? skillIndicator : "");

        System.out.printf(" %-10s: %-3d %s%n",
                p2.getPlayerName(),
                p2.getTotalScore(),
                p2.isCanSkill() ? skillIndicator : "");

    }


    private void handleDiceRollError() throws InterruptedException {
        System.out.println("\n 주사위 굴리기 오류 \n");
        getUtilView().userPage();
    }


    private void processGameEnd(Player player1, Player player2, DiceGame currentDiceGame) throws InterruptedException {
        displayFinalScores(player1, player2);
        
        if (player1.isCanSkill() || player2.isCanSkill()) {
            processSkillPhase(player1, player2);
        }
        
        if (!diceGameService.determineWinner(player1, player2, currentDiceGame)) {
            System.out.println("\n 승자 결정 오류");
        }
        
        diceGameService.finalizeGame(player1, player2, currentDiceGame);
        createAndDisplayBattleReport(currentDiceGame);
    }


    private void displayFinalScores(Player player1, Player player2) {
        System.out.println("\n=== 최종 점수 ===");
        System.out.printf("%s: %d점\n", player1.getPlayerName(), player1.getTotalScore());
        System.out.printf("%s: %d점\n\n", player2.getPlayerName(), player2.getTotalScore());
    }


    private void processSkillPhase(Player player1, Player player2) throws InterruptedException {
        List<Player> players = List.of(player1, player2);
        
        for (Player player : players) {
            if (player.isCanSkill()) {
                displaySkillPhaseUI(player1, player2, player);
                processPlayerSkillTurn(player, player1, player2);
            }
        }
    }

    private void displaySkillPhaseUI(Player player1, Player player2, Player currentPlayer) {
        System.out.println("\n=====================================");
        System.out.println("🎲🎯       [ 스킬 주사위 TIME! ]       🎯🎲");
        System.out.println("\n=====================================");
        displayScoreBoard(player1, player2);
        System.out.println("\n=====================================\n");
        displaySkillRules();
        System.out.println("\n=====================================\n");
        System.out.printf("👉   %s 님 차례 \n", currentPlayer.getPlayerName());
    }


    private void displaySkillRules() {
        System.out.println("📝 스킬 주사위 규칙:");
        System.out.println(" - 🎯 3이 나오면: 상대 점수 몰수!");
        System.out.println(" - 💀 4가 나오면: 자신 즉사...");
        System.out.println(" - ➕ 그 외의 수: 일반 주사위에 합산");
    }


    private void processPlayerSkillTurn(Player player, Player player1, Player player2) throws InterruptedException {
        waitForEnter("주사위 굴리기 🎲");
        displayDiceRollAnimation();
        
        DiceDTO skillDice = diceService.rollDice();
        displayDiceArt(skillDice);

        if (!diceGameService.processSkillDice(skillDice.getDiceNumber(), player, player1, player2)) {
            handleDiceRollError();
        }
    }

    private void createAndDisplayBattleReport(DiceGame currentDiceGame) throws InterruptedException {
        battleReportService.createBattleReport(Token.getInstance().getUser().getId(), currentDiceGame);
        battleReportView.battleReport();
    }

    public void skillDice() throws InterruptedException {
        List<Player> players = Token.getInstance().getUser().getCurrentDiceGame().getPlayers();
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        for (Player player : players) {
            if (player.isCanSkill()) {
                displaySkillPhaseUI(player1, player2, player);
                processPlayerSkillTurn(player, player1, player2);
            }
        }
    }

    public void error() throws InterruptedException {
        System.out.println();
        System.out.println(" 주사위 굴리기 오류 ");
        System.out.println();
        getUtilView().userPage();
    }

    public void diceResult(DiceDTO diceDTO) {
        for (String line : diceDTO.getDiceArt()) {
            System.out.println(line);
        }
    }

    public void DiceGameReady() throws InterruptedException {


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("       Dice Game을 시작 합니다      ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("+-------------------------------+");
        System.out.println("|             게임시작            |");
        System.out.println("|                               |");
        System.out.println("|          Start [Enter]        |");
        System.out.println("|                               |");
        System.out.println("+-------------------------------+");
        String input = UtilService.sc.nextLine();
        if (!input.isEmpty()) {

        } else{
//            test();
            playerView.addPlayer();


        }




    }


}
