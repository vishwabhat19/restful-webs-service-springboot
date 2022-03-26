package com.rest.webservice.restfulwebservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rest.webservice.restfulwebservice.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserDAOService {
    
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Neha", new Date()));
        users.add(new User(3, "Aastha", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id) {
        for(User user: users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
