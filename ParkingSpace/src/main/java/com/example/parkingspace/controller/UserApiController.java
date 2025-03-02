package com.example.parkingspace.controller;
import com.example.parkingspace.service.UserService;
import com.example.parkingspace.model.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserApiController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) String keyword) {
        return userService.listAll(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        Optional<User> duplicatedUser = userService.findDuplicates(user);
        if (duplicatedUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с таким регистрационным номером уже существует");
        }
        try{
            userService.save(user);
            return new ResponseEntity<>("Пользователь успешно создан", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании пользователя");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> existingUser = userService.getUser(id);
        if(existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        Optional<User> duplicatedUser = userService.findDuplicates(user);
        if (duplicatedUser.isPresent() && !duplicatedUser.get().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Пользователь с таким регистрационным номером уже существует");
        }

        try{
            userService.save(user);
            return ResponseEntity.ok("Данные пользователя успешно обновлены.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при обновлении данных пользователя.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        if(user.isEmpty()) {
            return ResponseEntity.ok("Пользователя с таким id не сущестсвует.");
        }

        try{
            userService.delete(id);
            return ResponseEntity.ok("Пользователь успешно удален.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка при удалении пользователя.");
        }

    }
}
