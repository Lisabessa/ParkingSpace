package com.example.parkingspace.service;
import com.example.parkingspace.model.*;

import java.util.List;
import java.util.Optional;

import com.example.parkingspace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll(String keyword){
        if(keyword == null || keyword.isEmpty()){
            return repo.findAll();
        }
        return repo.SearchUser(keyword);
    }

    public void save(User user){
        //user.setPassword(passwordEncoder().encode(user.getPassword()));
        repo.save(user);
    }

    public User getUser(String id){
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<User> getUser(Long id){
        return repo.findById(id);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public Optional<User> findDuplicates(User user){
         return repo.findByLogin(user.getLogin());
    }

    public void updateUser(User user) throws Exception {
        User existingUser = repo.findById(user.getId()).orElseThrow(() -> new RuntimeException("Пользователь не найден."));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        existingUser.setLogin(user.getLogin());
        existingUser.setRole(user.getRole());
        repo.save(existingUser);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
