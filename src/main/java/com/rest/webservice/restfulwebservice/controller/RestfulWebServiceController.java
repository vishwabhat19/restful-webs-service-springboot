package com.rest.webservice.restfulwebservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.rest.webservice.restfulwebservice.exceptions.UserNotFoundException;
import com.rest.webservice.restfulwebservice.model.User;
import com.rest.webservice.restfulwebservice.service.UserDAOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private MessageSource messageSource;    

    @Autowired
    UserDAOService service;

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternatioanlized() {
        return messageSource.getMessage("good.morning.message", null,"Default Message", LocaleContextHolder.getLocale());
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = service.findUser(id);
        if(null == user) {
            throw new UserNotFoundException("id - "+id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder mvcLinkBuilder = linkTo(methodOn(RestfulWebServiceController.class).getUsers());

        entityModel.add(mvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);
        if(null == user) {
            throw new UserNotFoundException("id - "+id);
        }
    }

}
