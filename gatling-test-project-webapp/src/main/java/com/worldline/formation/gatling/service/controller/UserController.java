package com.worldline.formation.gatling.service.controller;

import com.worldline.formation.gatling.service.model.User;
import com.worldline.formation.gatling.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

/**
 * Created by a501768 on 22/07/2016.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository repository;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public Iterable<User> getUsers() {
        return repository.findAll();
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User u) {
        LOGGER.debug("received post request for user {}",u);
        u.setId(UUID.randomUUID().toString());
        User newUser = repository.save(u);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri());
        return new ResponseEntity<>(repository.save(u), httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        LOGGER.debug("received get request for user with id {}",id);
        return repository.findOne(id);
    }

}

