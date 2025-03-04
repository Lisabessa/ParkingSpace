package com.example.parkingspace.repository;
import com.example.parkingspace.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p where CONCAT(p.firstName, ' ', p.lastName, ' ', p.phoneNumber, ' ', p.login) LIKE %?1%")
    List<User> SearchUser(String keyword);

    Optional<User> findByLogin(String login);

}
