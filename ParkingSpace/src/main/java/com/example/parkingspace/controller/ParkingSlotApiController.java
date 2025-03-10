package com.example.parkingspace.controller;
import com.example.parkingspace.service.ParkingSlotService;
import com.example.parkingspace.model.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/parkingSlots")
public class ParkingSlotApiController {
    @Autowired
    private ParkingSlotService parkingSlotService;

    @GetMapping
    public List<ParkingSlot> getParkingSlotsMethod(@RequestParam(required = false) String keyword) {
        return parkingSlotService.listAll(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSlot> getParkingSlotByIdMethod(@PathVariable Long id) {
        Optional<ParkingSlot> parkingSlot = parkingSlotService.getParkingSlot(id);
        return parkingSlot.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createParkingSlotMethod(@Valid @RequestBody ParkingSlot parkingSlot) {
        Optional<ParkingSlot> duplicatedParkingSlot = parkingSlotService.findDuplicates(parkingSlot);
        if(duplicatedParkingSlot.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Парковочный слот с таким кодом уже существует");
        }
        try{
            parkingSlotService.createParkingSlot(parkingSlot);
            return new ResponseEntity<>("Парковочный слот успешно создан", HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании парковочного слота");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParkingSlotMethod(@PathVariable Long id, @Valid @RequestBody ParkingSlot parkingSlot) {
        Optional<ParkingSlot> existingParkingSlot = parkingSlotService.getParkingSlot(id);
        if(existingParkingSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        parkingSlot.setId(id);
        Optional<ParkingSlot> duplicatedParkingSlot = parkingSlotService.findDuplicates(parkingSlot);
        if(duplicatedParkingSlot.isPresent() && !duplicatedParkingSlot.get().getId().equals(parkingSlot.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Парковочный слот с таким регистрационным номером уже существует!");
        }

        try{
            parkingSlotService.updateParkingSlot(parkingSlot);
            return ResponseEntity.ok("Данные парковочного слота успешно обновлены.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при обновлении данных парковочного слота");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParkingSlotMethod(@PathVariable Long id) {
        Optional<ParkingSlot> existingParkingSlot = parkingSlotService.getParkingSlot(id);
        if(existingParkingSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try{
            parkingSlotService.delete(id);
            return ResponseEntity.ok("Парковочный слот успешно удален.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении парковочного слота.");
        }
    }
}
