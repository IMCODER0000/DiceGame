package user.repository;

import user.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(String name, String loginId, String password);

    Optional<User> findByLoginId(String loginId);


}
