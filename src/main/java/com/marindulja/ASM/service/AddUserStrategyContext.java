package com.marindulja.ASM.service;

import com.marindulja.ASM.model.users.User;

public class AddUserStrategyContext {
    private AddUserStrategy addUserStrategy;
    AddUserStrategyContext(AddUserStrategy addUserStrategy){
        this.addUserStrategy = addUserStrategy;
    }
    User createUser() {
        return this.addUserStrategy.createUser();
    }

}
