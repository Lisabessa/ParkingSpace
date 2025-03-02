package com.example.parkingspace.repository;
import com.example.parkingspace.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    @Query("SELECT p from ParkingSlot p where CONCAT(p.slotCode, ' ', p.isAvailable) LIKE %?1%")
    List<ParkingSlot> SearchParkingSlot(String keyword);

    Optional<ParkingSlot> findBySlotCode(String slotCode);
}
