package com.example.parkingspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repo;

    public List<Reservation> listAll() {
        return repo.findAll();
    }

    public Reservation getReservation(String id) {
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<Reservation> getReservation(Long id) {
        return repo.findById(id);
    }

    public void save(Reservation reservation) {
        repo.save(reservation);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void updateReservation(Reservation reservation) throws Exception {
        Reservation existingReservation = repo.findById(reservation.getId()).orElseThrow(() -> new RuntimeException("Бронирование не найдено"));
        existingReservation.setStartTime(reservation.getStartTime());
        existingReservation.setEndTime(reservation.getEndTime());
        existingReservation.setStatus(reservation.getStatus());
        existingReservation.setUser(reservation.getUser());
        existingReservation.setParkingSlot(reservation.getParkingSlot());
        existingReservation.setPrice(reservation.getPrice());
        repo.save(existingReservation);
    }
}
