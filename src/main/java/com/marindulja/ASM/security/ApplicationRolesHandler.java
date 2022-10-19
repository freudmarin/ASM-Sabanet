package com.marindulja.ASM.security;

import com.marindulja.ASM.model.Role;
import com.marindulja.ASM.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import java.util.stream.Stream;

@Configuration
@Slf4j
public class ApplicationRolesHandler {

    private final RoleRepository roleRepository;

    public ApplicationRolesHandler(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void saveRolesToDataBase() {
        Stream.of(ApplicationRoles.values())
                .map(ApplicationRoles::getName)
                .map(Role::new)
                .map(roleRepository::saveIfNameNotExists)
                .forEach(x -> log.info("Created new role: {}", x.getName()));
    }

}
