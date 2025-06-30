package user.view;

import user.entity.User;
import user.repository.UserRepositoryImpl;
import user.service.UserService;
import user.service.UserServiceImpl;
import util.service.UtilService;
import util.token.Token;
import util.view.UtilView;

public class UserView {
    public static UserView instance;
    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
        }
        return instance;
    }

    private final UserService userService;

    public UserView(){
        this.userService = UserServiceImpl.getInstance();
    }

    private UtilView getUtilView() {
        return UtilView.getInstance();
    }

    public void register() throws InterruptedException {
        UtilService.sc.nextLine();
        System.out.println("            회원 가입            ");
        System.out.println();
        System.out.print(" 이름 : ");
        String name = UtilService.sc.nextLine();
        System.out.print(" 아이디 : ");
        String loginId = UtilService.sc.nextLine();
        System.out.print(" 비밀번호 : ");
        String pw= UtilService.sc.nextLine();


        User register = userService.register(name, loginId, pw);

        if(register != null){
            Token.getInstance().setUser(register);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("       회원 가입을 진심으로 축하드립니다       ");
            System.out.println();
            getUtilView().landingPage();


        } else{
            System.out.println("              회원 가입 오류            ");
            System.out.println();
        }


    }


    public void login() throws InterruptedException {

        UtilService.sc.nextLine();
        System.out.println("             로그인            ");
        System.out.println();
        System.out.print(" 아이디 : ");
        String loginId = UtilService.sc.nextLine();
        System.out.print(" 비밀번호 : ");
        String pw= UtilService.sc.nextLine();

        User login = userService.login(loginId, pw);

        if(login != null){
            Token.getInstance().setUser(login);
            System.out.println();
            System.out.println("            로그인 성공 !            ");
            System.out.println();
            getUtilView().userPage();
        } else {
            System.out.println();
            System.out.println("        아이디 또는 비밀번호가 다릅니다.       ");
            System.out.println();
            getUtilView().landingPage();

        }


    }



}
