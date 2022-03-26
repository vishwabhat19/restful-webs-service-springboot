package com.rest.webservice.restfulwebservice.controller;

import java.net.URI;
import java.util.List;

import com.rest.webservice.restfulwebservice.exceptions.UserNotFoundException;
import com.rest.webservice.restfulwebservice.model.User;
import com.rest.webservice.restfulwebservice.service.UserDAOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class RestfulWebServiceController {

    @Autowired
    UserDAOService service;

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = service.findUser(id);
        if(null == user) {
            throw new UserNotFoundException("id - "+id);
        }
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(null == user) {
            throw new UserNotFoundException("id - "+id);
        }
    }

}
