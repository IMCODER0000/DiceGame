package user.service;

import user.entity.User;
import user.repository.UserRepository;
import user.repository.UserRepositoryImpl;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    public static UserServiceImpl instance;
    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private final UserRepository userRepository;

    private UserServiceImpl() {
        this.userRepository = UserRepositoryImpl.getInstance();
    }


    @Override
    public User register(String name, String loginId, String password) {
        return userRepository.save(name, loginId, password);
    }

    @Override
    public User login(String loginId, String password) {
        Optional<User> LoginUser = userRepository.findByLoginId(loginId);
        if (LoginUser.isPresent()) {
            return LoginUser.get();
        }
        return null;
    }
}
