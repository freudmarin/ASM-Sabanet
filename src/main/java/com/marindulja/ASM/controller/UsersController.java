package com.marindulja.ASM.controller;

import com.marindulja.ASM.model.Role;
import com.marindulja.ASM.model.User;
import com.marindulja.ASM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userDetailsService;

    /*{
        "username" :"technichian1",
            "password" :"12345",
            "fullName" :"Test Test",
            "role" :
        {"name":"TECHNICHIAN"}

    }*/
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        userDetailsService.addUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userDetailsService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
       return userDetailsService.getUserById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return userDetailsService.updateUserById(id, user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        return userDetailsService.deleteUserById(id);
    }
}
