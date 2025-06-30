package user.service;

import user.entity.User;

import java.util.Optional;

public interface UserService {

    User register(String name, String loginId, String password);
    User login(String loginId, String password);
    User getUserById(Long id);


}
