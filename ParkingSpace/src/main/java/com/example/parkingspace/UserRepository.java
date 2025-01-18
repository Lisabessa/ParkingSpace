package com.example.parkingspace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p where CONCAT(p.vehicle_registration_number, ' ', p.vehicle_model, ' ', p.vehicle_color, ' ',  p.first_name, ' ', p.last_name, ' ', p.phone_number) LIKE %?1%")
    List<User> SearchUser(String keyword);
}
