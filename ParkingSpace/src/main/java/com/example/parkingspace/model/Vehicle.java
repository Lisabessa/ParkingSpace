package com.example.parkingspace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Table(name = "vehicles")
@Setter
@Entity
public class Vehicle {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @NotBlank(message = "Регистрационный номер автомобиля не должен быть пустым")
    @Getter
    private String vehicleRegistrationNumber;

    @Getter
    private String vehicleModel;

    @Getter
    private String vehicleColor;

    private User user;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }
}
