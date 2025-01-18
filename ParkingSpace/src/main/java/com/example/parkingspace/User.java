package com.example.parkingspace;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Table(name = "users")
@Setter
@Entity
public class User {
    private Long id;

    @Getter
    private String vehicle_registration_number;

    @Getter
    private String vehicle_model;

    @Getter
    private String vehicle_color;

    @Getter
    private String first_name;

    @Getter
    private String last_name;

    @Getter
    private String phone_number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}
