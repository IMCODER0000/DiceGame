package util.service;

import java.util.Scanner;

public class UtilService {

    public static Scanner sc = new Scanner(System.in);


    public static void waitForEnter(String label) {
        while (true) {
            System.out.println("+-------------------------------+");
            System.out.printf("|   %s [Enter] 입력해주세요   |\n", label);
            System.out.println("+-------------------------------+");
            String input = UtilService.sc.nextLine();
            if (input.isEmpty()) break;
            System.out.println("❗ 오류! [Enter] 키만 눌러야 진행됩니다.\n");
        }
        return;
    }




}
