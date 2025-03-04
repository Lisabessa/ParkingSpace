package com.example.parkingspace.service;

import com.example.parkingspace.model.*;
import com.example.parkingspace.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repo;

    public List<Role> listAll() {return repo.findAll();}

    public Role getRole(String id) {
        return repo.findById(Long.valueOf(id)).get();
    }

    public Optional<Role> getRole(Long id) {
        return repo.findById(id);
    }

    public void createRole(Role role) {
        repo.save(role);
    }

    public void updateRole(Role role) {
        repo.save(role);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Optional<Role> findDuplicates(Role role) {
        return repo.findByRole(role.getRole());
    }
}
