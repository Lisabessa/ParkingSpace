package com.example.parkingspace;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;

@Table(name = "users")
@Setter
@Entity
public class User {
    private Long id;

    @NotBlank(message = "Регистрационный номер автомобиля не должен быть пустым")
    @Getter
    private String vehicleRegistrationNumber;

    @Getter
    private String vehicleModel;

    @Getter
    private String vehicleColor;

    @NotBlank(message = "Имя не должно быть пустым")
    @Getter
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Getter
    private String lastName;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Getter
    private String phoneNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
