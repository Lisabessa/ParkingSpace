package com.example.parkingspace.service;

import com.example.parkingspace.model.*;
import com.example.parkingspace.repository.VehicleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repo;

    public List<Vehicle> listAll(String keyword) {
        if(keyword == null || keyword.isEmpty()){
            return repo.findAll();
        }
        return repo.SearchVehicle(keyword);
    }

    public Vehicle getVehicle(String id) {
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<Vehicle> getVehicle(Long id) {
        return repo.findById(id);
    }

    public void createVehicle(Vehicle vehicle) {
        repo.save(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        repo.save(vehicle);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Optional<Vehicle> findDuplicates(Vehicle vehicle) {
        return repo.findByVehicleRegistrationNumber(vehicle.getVehicleRegistrationNumber());
    }
}
