package com.rest.webservice.restfulwebservice.model;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have atleast two characters!")
    private String name;

    @Past(message = "Birthdate should be a past date!")
    private Date birthDate;

    public User() {

    }
    

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Date getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    
    
    
}
