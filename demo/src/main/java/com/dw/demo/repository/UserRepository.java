package com.dw.demo.repository;

import com.dw.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userID);
    Optional<User> findByUserName(String userName);
}
