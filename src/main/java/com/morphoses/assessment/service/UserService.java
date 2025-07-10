package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.exception.EntityNotFoundException;
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserType userType, String name) {
        User user = new User();
        user.setUserType(userType);
        user.setName(name);
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));
    }
}