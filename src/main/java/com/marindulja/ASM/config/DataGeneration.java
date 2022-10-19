package com.marindulja.ASM.config;

import com.marindulja.ASM.model.User;
import com.marindulja.ASM.repository.RoleRepository;
import com.marindulja.ASM.repository.UserRepository;
import com.marindulja.ASM.security.ApplicationRoles;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
@DependsOn("applicationRolesHandler")
public class DataGeneration {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public DataGeneration(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void generateData() {
        log.info("Starting to add dummy data!");


       if (userRepository.count() ==0) {
            List<User> users = Arrays.asList(
                    new User("admin", passwordEncoder.encode("12345"),"Marin Dulja",
                            roleRepository.findByName(ApplicationRoles.Identifier.ADMIN).get()));

            userRepository.saveAll(users);
        }
    }
}
