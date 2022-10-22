package com.marindulja.ASM.controller;

import com.marindulja.ASM.model.users.Acceptance;
import com.marindulja.ASM.model.users.Technician;
import com.marindulja.ASM.model.users.User;
import com.marindulja.ASM.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/addTechnician")
    public ResponseEntity addTechnician(@Valid @RequestBody Technician technician) {
        userDetailsService.addUser(technician);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/addAcceptance")
    public ResponseEntity addAcceptance(@Valid @RequestBody Acceptance acceptance) {
        userDetailsService.addUser(acceptance);
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
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @Valid @RequestBody User user) {
        return userDetailsService.updateUserById(id, user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        return userDetailsService.deleteUserById(id);
    }
}
