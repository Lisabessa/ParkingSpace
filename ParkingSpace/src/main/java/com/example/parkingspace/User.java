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
    private String vehicle_registration_number;

    @NotBlank(message = "Марка автомобиля не должна быть пустой")
    @Getter
    private String vehicle_model;

    @NotBlank(message = "Цвет автомобиля не должен быть пустым")
    @Getter
    private String vehicle_color;

    @NotBlank(message = "Имя не должно быть пустым")
    @Getter
    private String first_name;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Getter
    private String last_name;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Getter
    private String phone_number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
