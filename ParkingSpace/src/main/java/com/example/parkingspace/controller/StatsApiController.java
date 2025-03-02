package com.example.parkingspace.controller;

import com.example.parkingspace.model.ParkingSlot;
import com.example.parkingspace.service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/stats")
public class StatsApiController {
    @Autowired
    private ParkingSlotService parkingSlotService;

    @GetMapping("/parkingSlots")
    public Map<String, Integer> getSlotsStats() {
        Integer available = parkingSlotService.countAvailableSlots();
        Integer occupied = parkingSlotService.countOccupiedSlots();
        Map<String, Integer> stats = new HashMap<>();
        stats.put("available", available);
        stats.put("occupied", occupied);
        return stats;
    }
}
