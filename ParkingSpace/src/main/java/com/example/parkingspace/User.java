package com.example.parkingspace;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import lombok.Getter;

@Setter
@Entity
public class User {
    private int id;

    @Getter
    private String first_name;

    @Getter
    private String last_name;

    @Getter
    private String phone_number;

    @Getter
    private String password;

    @Getter
    private int role_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
}
