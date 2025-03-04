package com.example.parkingspace.controller;

import com.example.parkingspace.model.*;
import com.example.parkingspace.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/roles")
public class RoleApiController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getRolesMethod() {
        return roleService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleByIdMethod(@PathVariable Long id) {
        Optional<Role> role = roleService.getRole(id);
        return role.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createRoleMethod(@Valid @RequestBody Role role) {
        Optional<Role> duplicatedRole = roleService.findDuplicates(role);
        if (duplicatedRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Роль с таким названием уже существует.");
        }
        try{
            roleService.createRole(role);
            return new ResponseEntity<>("Новая роль успешно создана", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании роли");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoleMethod(@PathVariable Long id, @Valid @RequestBody Role role) {
        Optional<Role> existingRole = roleService.getRole(id);
        if(existingRole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        role.setId(id);
        Optional<Role> duplicatedRole = roleService.findDuplicates(role);
        if (duplicatedRole.isPresent() && !duplicatedRole.get().getId().equals(role.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Роль с таким названием уже существует");
        }

        try{
            roleService.updateRole(role);
            return ResponseEntity.ok("Данные роли успешно обновлены.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при обновлении данных о роли.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoleMethod(@PathVariable Long id) {
        Optional<Role> role = roleService.getRole(id);
        if(role.isEmpty()) {
            return ResponseEntity.ok("Роли с таким id не сущестсвует.");
        }

        try{
            roleService.delete(id);
            return ResponseEntity.ok("Роль успешно удалена.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении роли.");
        }

    }
}
