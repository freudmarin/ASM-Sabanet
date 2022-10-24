package com.marindulja.ASM.service;

import com.marindulja.ASM.model.users.User;

public class AddUserStrategyExe {
    private AddUserStrategy addUserStrategy;
    AddUserStrategyExe(AddUserStrategy addUserStrategy){
        this.addUserStrategy = addUserStrategy;
    }
    User createUser() {
        return this.addUserStrategy.createUser();
    }

}
