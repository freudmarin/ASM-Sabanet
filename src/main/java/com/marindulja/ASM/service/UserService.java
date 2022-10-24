package com.marindulja.ASM.service;


import com.marindulja.ASM.dto.UserDto;
import com.marindulja.ASM.exception.ResourceNotFoundException;
import com.marindulja.ASM.model.users.User;
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
    List<UserDto> getAllUsers();

    /**
     * Find a user by its username
     *
     * @param username the username to search for
     * @return the user with the given username
     * @throws ResourceNotFoundException when the username does not exist
     */
    User findByUsername(String username) throws ResourceNotFoundException;

    UserDto addUser(UserDto user);
    ResponseEntity<UserDto> getUserById(long id);
    ResponseEntity<UserDto> updateUserById(long id, UserDto user);
    ResponseEntity<HttpStatus> deleteUserById(long id);
}
