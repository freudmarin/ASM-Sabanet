package com.marindulja.ASM.controller;

import com.marindulja.ASM.dto.AcceptanceDto;
import com.marindulja.ASM.dto.TechnicianDto;
import com.marindulja.ASM.dto.UserDto;
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
    public ResponseEntity<TechnicianDto> addTechnician(@Valid @RequestBody TechnicianDto technicianDto) {
        return new ResponseEntity(userDetailsService.addUser(technicianDto), HttpStatus.OK);
    }

    @PostMapping("/addAcceptance")
    public ResponseEntity addAcceptance(@Valid @RequestBody AcceptanceDto acceptanceDto) {
        return new ResponseEntity(userDetailsService.addUser(acceptanceDto), HttpStatus.OK);
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        return userDetailsService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") long id) {
       return userDetailsService.getUserById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") long id, @Valid @RequestBody UserDto userDto) {
        return userDetailsService.updateUserById(id, userDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        return userDetailsService.deleteUserById(id);
    }
}
