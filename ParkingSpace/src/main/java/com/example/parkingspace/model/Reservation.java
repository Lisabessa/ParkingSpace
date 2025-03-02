package com.example.parkingspace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания бронирования не должно быть пустым")
    @Getter
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;

    private User user;

    private ParkingSlot parkingSlot;


    @ManyToOne
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
