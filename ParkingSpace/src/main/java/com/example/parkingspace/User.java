package com.example.parkingspace;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

@Table(name = "users")
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
