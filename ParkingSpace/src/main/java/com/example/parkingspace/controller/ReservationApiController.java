package com.example.parkingspace.controller;
import com.example.parkingspace.service.ReservationService;
import com.example.parkingspace.model.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reservations")
public class ReservationApiController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservationsMethod() {
        return reservationService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationMethod(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservation(id);
        return reservation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createReservationMethod(@Valid @RequestBody Reservation reservation) {
        try{
            reservationService.createReservation(reservation);
            return new ResponseEntity<>("Новое бронирование успешно создано.", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании бронирования");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservationMethod(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
        Optional<Reservation> existingReservation = reservationService.getReservation(id);
        if (existingReservation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reservation.setId(id);
        try{
            reservationService.updateReservation(reservation);
            return ResponseEntity.ok("Данные о бронировании успешно изменены.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при изменении данных о бронировании.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservationMethod(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservation(id);
        if (reservation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            reservationService.delete(id);
            return ResponseEntity.ok("Бронирование успешно удалено.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении бронирования.");
        }
    }

}
