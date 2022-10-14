package com.dockerspring.controllers;

import com.dockerspring.entities.User;
import com.dockerspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/")
    public void create(@RequestBody List<UserPayload> payloads) {
       for (UserPayload payload : payloads) {
           User user = new User();
           user.setAge(payload.getAge());
           user.setName(payload.getName());
           user.setEmail(payload.getEmail());
           user.setPassword(payload.getPassword());

           userService.createUser(user);
       }
    }
}
