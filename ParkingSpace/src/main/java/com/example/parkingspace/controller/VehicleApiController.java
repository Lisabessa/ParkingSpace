package com.example.parkingspace.controller;

import com.example.parkingspace.model.User;
import com.example.parkingspace.model.Vehicle;
import com.example.parkingspace.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/vehicles")
public class VehicleApiController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getVehiclesMethod(@RequestParam(required = false) String keyword) {
        return vehicleService.listAll(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleByIdMethod(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicle(id);
        return vehicle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createVehicleMethod(@Valid @RequestBody Vehicle vehicle) {
        Optional<Vehicle> duplicatedVehicle = vehicleService.findDuplicates(vehicle);
        if (duplicatedVehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ТС с таким регистрационным номером уже существует.");
        }
        try{
            vehicleService.createVehicle(vehicle);
            return new ResponseEntity<>("ТС успешно создано.", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании ТС.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicleMethod(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {
        Optional<Vehicle> existingVehicle = vehicleService.getVehicle(id);
        if(existingVehicle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehicle.setId(id);
        Optional<Vehicle> duplicatedVehicle = vehicleService.findDuplicates(vehicle);
        if (duplicatedVehicle.isPresent() && !duplicatedVehicle.get().getId().equals(vehicle.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ТС с таким регистрационным номером уже существует.");
        }

        try{
            vehicleService.updateVehicle(vehicle);
            return ResponseEntity.ok("Данные ТС успешно обновлены.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при обновлении данных ТС.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicleMethod(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.getVehicle(id);
        if(vehicle.isEmpty()) {
            return ResponseEntity.ok("ТС с таким id не сущестсвует.");
        }

        try{
            vehicleService.delete(id);
            return ResponseEntity.ok("ТС успешно удалено.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении ТС.");
        }

    }
}
