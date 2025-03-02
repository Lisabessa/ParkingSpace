package com.example.parkingspace.service;
import com.example.parkingspace.model.*;

import com.example.parkingspace.repository.ParkingSlotRepository;
import com.example.parkingspace.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repo;

    @Autowired
    private ParkingSlotRepository repo_ps;

    public List<Reservation> listAll() {
        return repo.findAll();
    }

    public Reservation getReservation(String id) {
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<Reservation> getReservation(Long id) {
        return repo.findById(id);
    }

    public void createReservation(Reservation reservation) {
        ParkingSlot ps = repo_ps.findById(reservation.getParkingSlot().getId()).get();
        ps.setIsAvailable(false);
        repo_ps.save(ps);
        repo.save(reservation);
    }

    public void delete(Long id) {
        Reservation r = repo.findById(id).get();
        ParkingSlot ps = repo_ps.findById(r.getParkingSlot().getId()).get();
        ps.setIsAvailable(true);
        repo_ps.save(ps);
        repo.deleteById(id);
    }

    public void updateReservation(Reservation reservation){
        Reservation old = repo.findById(reservation.getId()).get();
        ParkingSlot ps_old = repo_ps.findById(old.getParkingSlot().getId()).get();
        ParkingSlot ps_new = repo_ps.findById(reservation.getParkingSlot().getId()).get();
        if(!ps_new.getSlotCode().equals(ps_old.getSlotCode())){
            ps_old.setIsAvailable(true);
            ps_new.setIsAvailable(false);
            repo_ps.save(ps_old);
            repo_ps.save(ps_new);
        }
        repo.save(reservation);
    }
}
