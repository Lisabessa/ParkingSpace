package com.example.parkingspace;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "reservations")
@Setter
@Entity
public class Reservation {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Getter
    private LocalDateTime startTime;

    @Getter
    private LocalDateTime endTime;

    private User user;

    private ParkingSlot parkingSlot;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser(){
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "parking_slot_id")
    public ParkingSlot getParkingSlot(){
        return parkingSlot;
    }

    @Getter
    public String status;
    @Getter
    private Double price;


}
