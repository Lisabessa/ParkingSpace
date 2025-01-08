package com.example.parkingspace;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
        repo.save(user);
    }

    public User getUser(String id){
        return repo.findById(Long.valueOf(id)).get();
    }

    public void delete(String id){
        repo.deleteById(Long.valueOf(id));
    }
}
