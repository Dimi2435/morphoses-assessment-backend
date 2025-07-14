package com.morphoses.assessment.service;

import com.morphoses.assessment.entity.User;
import com.morphoses.assessment.exception.UserNotFoundException;
import com.morphoses.assessment.repository.UserRepository;
import com.morphoses.assessment.util.UserType;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users in the Morphoses Assessment application.
 *
 * This class provides methods to create and retrieve users.
 *
 * Author: Dimitrios Milios
 */
@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    /**
     * Creates a new user with the specified user type and name.
     *
     * @param userType the type of user to create
     * @param name the name of the user
     * @return the created User object
     */
    public User createUser(UserType userType, String name) {
        User user = new User();
        user.setUserType(userType);
        user.setName(name);
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the unique ID of the user
     * @return the User object associated with the given ID
     * @throws UserNotFoundException if no user is found with the given ID
     */
    public User getUserById(UUID id) {
        return userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }
}
