package com.example.parkingspace.repository;
import com.example.parkingspace.model.*;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatus(String status);
    List<Reservation> findByStartTime(LocalDateTime startTime);
    List<Reservation> findByEndTime(LocalDateTime endTime);
    List<Reservation> findByPrice(Double price);

    List<Reservation> findByVehicle(Vehicle vehicle);
    List<Reservation> findByParkingSlot(ParkingSlot parkingSlot);

}
