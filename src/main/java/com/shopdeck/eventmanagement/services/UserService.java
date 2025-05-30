package com.shopdeck.eventmanagement.services;

import com.shopdeck.eventmanagement.models.User;
import com.shopdeck.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }
}

