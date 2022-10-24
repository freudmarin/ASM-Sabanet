package com.marindulja.ASM.service;

import com.marindulja.ASM.model.users.Acceptance;
import com.marindulja.ASM.model.users.User;

public class AddAcceptanceService implements AddUserStrategy{

    @Override
    public User createUser() {
        return new Acceptance();
    }
}
