package com.marindulja.ASM.service;


import com.marindulja.ASM.exception.ResourceNotFoundException;
import com.marindulja.ASM.model.Role;
import com.marindulja.ASM.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * Gets all User stored into the system
     *
     * @return all users stored into the system
     */
    List<User> getAllUsers();
    List<Role> getAllRoles();

    /**
     * Find a user by its username
     *
     * @param username the username to search for
     * @return the user with the given username
     * @throws ResourceNotFoundException when the username does not exist
     */
    User findByUsername(String username) throws ResourceNotFoundException;

    void addUser(User userToBeAdded);
    ResponseEntity<User> getUserById(long id);
    ResponseEntity<User> updateUserById(long id, User user);
    ResponseEntity<HttpStatus> deleteUserById(long id);
}
