package com.example.parkingspace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository repo;

    public List<Reservation> listAll() {
        return repo.findAll();
    }

    public void save(Reservation reservation) {
        repo.save(reservation);
    }

    public void delete(String id) {
        repo.deleteById(Long.valueOf(id));
    }
}
