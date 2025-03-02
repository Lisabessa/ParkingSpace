package com.example.parkingspace.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Table(name = "parking_slots")
@Setter
@Entity
public class ParkingSlot {
    private Long id;

    @NotBlank(message = "Код слота не должен быть пустым")
    @Getter
    private String slotCode;

    @Getter
    private Boolean isAvailable;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
