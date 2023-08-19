package net.hostelHub.identitymanagement.repository;

import net.hostelHub.identitymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsernameOrEmail(String username, String email);
//    boolean existsByEmail(String email);
//    boolean existsByUsername(String username);
    boolean existsByEmailOrUsername(String email, String username);
    User findByEmailOrUsername(String email, String username);
}
