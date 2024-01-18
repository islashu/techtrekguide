package com.example.complete.service;

import com.example.complete.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public void saveUser(User user);

    public User getUserById(Long id);
}
