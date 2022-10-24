package com.marindulja.ASM.service;

import com.marindulja.ASM.model.users.Technician;
import com.marindulja.ASM.model.users.User;

public class AddTechnicianService implements AddUserStrategy {
    @Override
    public User createUser() {
        return new Technician();
    }
}

