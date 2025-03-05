package com.example.parkingspace.repository;

import com.example.parkingspace.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT p FROM Vehicle p where CONCAT(p.vehicleRegistrationNumber, ' ', p.vehicleModel, ' ', p.vehicleColor, ' ', p.user.firstName, ' ', p.user.lastName, ' ', p.user.phoneNumber, ' ', p.user.login) LIKE %?1%")
    List<Vehicle> SearchVehicle(String keyword);

    Optional<Vehicle> findByVehicleRegistrationNumber(String vehicleRegistrationNumber);
    List<Vehicle> findByUserId(Long userId);
}
