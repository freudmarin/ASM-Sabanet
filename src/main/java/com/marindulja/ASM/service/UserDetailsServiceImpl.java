package com.marindulja.ASM.service;

import com.marindulja.ASM.dto.AcceptanceDto;
import com.marindulja.ASM.dto.TechnicianDto;
import com.marindulja.ASM.dto.UserDto;
import com.marindulja.ASM.exception.ASMException;
import com.marindulja.ASM.exception.ResourceNotFoundException;
import com.marindulja.ASM.model.users.Acceptance;
import com.marindulja.ASM.model.users.Technician;
import com.marindulja.ASM.model.users.User;
import com.marindulja.ASM.repository.RoleRepository;
import com.marindulja.ASM.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private ModelMapper mapper = new ModelMapper();

    public UserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) throws ResourceNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("User with the username %s does not exist!", username))
                );
    }


    public UserDto addUser(UserDto userToBeAddedDto) {
        User user;
        ;
        if (userToBeAddedDto instanceof AcceptanceDto) {
            AddUserStrategyExe addUserStrategyExe = new AddUserStrategyExe(new AddAcceptanceService());
            user = addUserStrategyExe.createUser();
        } else {
            AddUserStrategyExe addUserStrategyExe = new AddUserStrategyExe(new AddTechnicianService());
            user = addUserStrategyExe.createUser();
        }
        user.setUsername(userToBeAddedDto.getUsername());
        user.setPassword(passwordEncoder.encode(userToBeAddedDto.getPassword()));
        user.setFullName(userToBeAddedDto.getFullName());
        user.setRole(roleRepository.findByName(userToBeAddedDto.getRole().getName()).orElseThrow(() -> new ResourceNotFoundException("This role was not found")));
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public ResponseEntity<UserDto> getUserById(long id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(mapToDTO(userData.get()), HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    public ResponseEntity<UserDto> updateUserById(long id, UserDto userDto) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            if (userDto.getRole() != null) {
                throw new ASMException(HttpStatus.BAD_REQUEST, "Sorry, but the role of the user is not updatabale");
            }
            _user.setUsername(userDto.getUsername());
            _user.setFullName(userDto.getFullName());
            _user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User updatedUser = userRepository.save(_user);

            return new ResponseEntity<>(mapToDTO(updatedUser), HttpStatus.OK);
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

    private final PasswordEncoder passwordEncoder;

    private UserDto mapToDTO(User user) {
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    private User mapToEntity(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        return user;
    }

    private AcceptanceDto mapToDTO(Acceptance acceptance) {
        AcceptanceDto acceptanceDto = mapper.map(acceptance, AcceptanceDto.class);
        return acceptanceDto;
    }

    private Acceptance mapToEntity(AcceptanceDto acceptanceDto) {
        Acceptance acceptance = mapper.map(acceptanceDto, Acceptance.class);
        return acceptance;
    }

    private TechnicianDto mapToDTO(Technician technician) {
        TechnicianDto technicianDto = mapper.map(technician, TechnicianDto.class);
        return technicianDto;
    }

    private Technician mapToEntity(TechnicianDto technicianDto) {
        Technician technician = mapper.map(technicianDto, Technician.class);
        return technician;
    }
}
