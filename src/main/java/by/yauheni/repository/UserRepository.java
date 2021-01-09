package by.yauheni.repository;

import by.yauheni.entity.user.Role;
import by.yauheni.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(long id);
    User findUserByUsername(String username);
    List<User> getUserByRole(Role role);
}
