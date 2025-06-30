package user.repository;


import user.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {

    private static Long userId = 0L;

    public static UserRepositoryImpl instance;
    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    private final Map<Long, User> users = new ConcurrentHashMap<>();


    @Override
    public User save(String name, String loginId, String password) {
            Long id = ++userId;

            User newUser = new User(id, name, loginId, password);
            users.put(id, newUser);
            return newUser;

    }

    @Override
    public Optional<User> findByLoginId(String loginId) {
        return users.values().stream()
                .filter(u -> u.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public User getUserById(Long id) {
        return users.get(id);
    }

}
