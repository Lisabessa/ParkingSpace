package com.example.parkingspace;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Время начала бронирования не должно быть пустым")
    @Getter
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания бронирования не должно быть пустым")
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
