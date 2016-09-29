package com.worldline.formation.gatling.service.controller;

import com.worldline.formation.gatling.service.model.User;
import com.worldline.formation.gatling.service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public User createUser(@RequestBody User u) {
        LOGGER.debug("received post request for user {}",u);
        u.setId(UUID.randomUUID().toString());
        return repository.save(u);
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        LOGGER.debug("received get request for user with id {}",id);
        return repository.findOne(id);
    }

}
