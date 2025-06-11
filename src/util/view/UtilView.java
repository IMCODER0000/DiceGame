package util.view;

import util.service.UtilService;

public class UtilView {

    public static UtilView instance = new UtilView();

    public static UtilView getInstance() {
        if (instance == null) {
            instance = new UtilView();
        }
        return instance;
    }


    public void landingPage() {

        System.out.println("      Dice 게임에 오신걸 환영 합니다.    ");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 회원가입 ");
        System.out.println(" 2. 로그인 ");

    }

    public void userPage() {

        System.out.println("   000님  Dice 게임에 오신걸 환영 합니다.    ");
        System.out.println();
        System.out.println();
        System.out.println(" 1. 게임 시작 ");
        System.out.println(" 2. 로그아웃 ");
        System.out.println(" 3. 종료 ");
        int input = UtilService.sc.nextInt();
        if (input == 1) {

        } else if(input == 2) {

        } else if(input == 3) {
            System.exit(0);
        } else{
            System.out.println("잘못된 숫자를 입력 하셨습니다 .");
            userPage();
        }



    }




}
