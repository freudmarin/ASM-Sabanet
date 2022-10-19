package com.marindulja.ASM.service;

import com.marindulja.ASM.exception.ResourceNotFoundException;
import com.marindulja.ASM.model.Role;
import com.marindulja.ASM.model.User;
import com.marindulja.ASM.repository.RoleRepository;
import com.marindulja.ASM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User findByUsername(String username) throws ResourceNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with the username %s does not exist!", username))
                );
    }

    public void addUser(User userToBeAdded) {
        User user = new User();
        user.setUsername(userToBeAdded.getUsername());
        user.setPassword(passwordEncoder.encode(userToBeAdded.getPassword()));
        user.setFullName(userToBeAdded.getFullName());
        user.setRole(roleRepository.findByName(userToBeAdded.getRole().getName()).orElseThrow(() -> new ResourceNotFoundException("This role was not found")));
        userRepository.save(user);
    }

    public ResponseEntity<User> getUserById(long id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public ResponseEntity<User> updateUserById(long id, User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setRole(roleRepository.findByName(user.getRole().getName())
                    .orElseThrow(() -> new ResourceNotFoundException("This role was not found")));
            _user.setUsername(user.getUsername());
            _user.setFullName(user.getFullName());
            _user.setPassword(passwordEncoder.encode(user.getPassword()));
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public ResponseEntity<HttpStatus> deleteUserById(long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user for authentication spring security");
        try {
            User user = findByUsername(username);

            String role = user.getRole().getName();
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_" + role);

            return new org.springframework.security.core.userdetails.User(
                    Long.toString(user.getId()),
                    user.getPassword(),
                    grantedAuthorities);

        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

}
