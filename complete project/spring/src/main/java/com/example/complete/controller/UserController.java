package com.example.complete.controller;

import com.example.complete.model.User;
import com.example.complete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        System.out.println("deserialisation"+ user.toString());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/createUser")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        System.out.println("Creating user: " + user.toString());
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }
}

