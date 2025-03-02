package com.example.parkingspace.service;
import com.example.parkingspace.model.*;

import com.example.parkingspace.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSlotService {
    @Autowired
    private ParkingSlotRepository repo;

    public List<ParkingSlot> listAll(String keyword){
        if(keyword == null || keyword.isEmpty()){
            return repo.findAll();
        }
        return repo.SearchParkingSlot(keyword);
    };

    public void save(ParkingSlot parkingSlot){
        repo.save(parkingSlot);
    }

    public ParkingSlot getParkingSlot(String id){
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<ParkingSlot> getParkingSlot(Long id){
        return repo.findById(id);
    }

    public void delete(Long id){
        repo.deleteById(Long.valueOf(id));
    }

    public Optional<ParkingSlot> findDuplicates(ParkingSlot parkingSlot){
        return repo.findBySlotCode(parkingSlot.getSlotCode());
    }

    public void updateParkingSlot(ParkingSlot parkingSlot) throws Exception{
        ParkingSlot existingParkingSlot = repo.findById(parkingSlot.getId()).orElseThrow(() -> new RuntimeException("Парковочное место не найдено"));
        existingParkingSlot.setSlotCode(parkingSlot.getSlotCode());
        existingParkingSlot.setIsAvailable(parkingSlot.getIsAvailable());
        repo.save(existingParkingSlot);
    }

    public Integer countAvailableSlots() {
        Integer count = 0;
        for (ParkingSlot parkingSlot : repo.findAll()) {
            if (parkingSlot.getIsAvailable()) {
                count++;
            }
        }

        return count;
    }

    public Integer countOccupiedSlots() {
        Integer count = 0;
        for (ParkingSlot parkingSlot : repo.findAll()) {
            if (!parkingSlot.getIsAvailable()) {
                count++;
            }
        }

        return count;
    }
}
