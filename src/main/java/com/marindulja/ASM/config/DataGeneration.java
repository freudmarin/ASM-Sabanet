package com.marindulja.ASM.config;

import com.marindulja.ASM.model.users.Admin;
import com.marindulja.ASM.repository.RoleRepository;
import com.marindulja.ASM.repository.UserRepository;
import com.marindulja.ASM.security.ApplicationRoles;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        log.info("Starting to generate admin!");
        if (userRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("12345"));
            admin.setFullName("Marin Dulja");
            admin.setRole(roleRepository.findByName(ApplicationRoles.Identifier.ADMIN).get());
            userRepository.save(admin);
        }
    }
}
