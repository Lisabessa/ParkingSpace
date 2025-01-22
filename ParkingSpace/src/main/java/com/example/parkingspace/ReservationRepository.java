package com.example.parkingspace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByStatus(String status);
    List<Reservation> findByStartTime(LocalDateTime startTime);
    List<Reservation> findByEndTime(LocalDateTime endTime);
    List<Reservation> findByPrice(Double price);

    List<Reservation> findByUser(User user);
    List<Reservation> findByParkingSlot(ParkingSlot parkingSlot);

}
