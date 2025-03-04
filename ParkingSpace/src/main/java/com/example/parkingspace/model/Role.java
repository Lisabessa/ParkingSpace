package com.example.parkingspace.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Table(name = "roles")
@Setter
@Entity
public class Role {
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {return id;}

    @NotBlank(message = "Наименование роли не должно быть пустым")
    @Getter
    private String role;

    @Getter
    private Integer code;
}
