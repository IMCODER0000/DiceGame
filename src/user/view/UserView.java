package user.view;

import user.entity.User;
import user.repository.UserRepositoryImpl;
import user.service.UserService;
import user.service.UserServiceImpl;
import util.service.UtilService;
import util.token.Token;

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
        userService = UserServiceImpl.getInstance();
    }

    public void register() {
        System.out.println("            회원 가입            ");
        System.out.println();
        System.out.println(" 이름 : ");
        String name = UtilService.sc.nextLine();
        System.out.println(" 아이디 : ");
        String loginId = UtilService.sc.nextLine();
        System.out.println(" 비밀번호 : ");
        String pw= UtilService.sc.nextLine();

        User register = userService.register(name, loginId, pw);

        if(register != null){
            Token.getInstance().setUser(register);
            System.out.println("       회원 가입을 진심으로 축하드립니다       ");
            System.out.println();

        } else{
            System.out.println("              회원 가입 오류            ");
            System.out.println();
        }


    }



}
