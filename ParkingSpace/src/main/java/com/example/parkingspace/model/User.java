package com.example.parkingspace.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import jakarta.validation.constraints.NotBlank;

@Table(name = "users")
@Setter
@Entity
public class User {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @NotBlank(message = "Имя не должно быть пустым")
    @Getter
    private String firstName;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Getter
    private String lastName;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Getter
    private String phoneNumber;

    private Role role;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role getRole() {
        return role;
    }

    @NotBlank(message = "Логин не должен быть пустым")
    @Getter
    private String login;

    @Getter
    private String password;
}
