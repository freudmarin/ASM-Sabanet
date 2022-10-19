package com.marindulja.ASM.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationRoles {
    ADMIN(Identifier.ADMIN),
    ACCEPTANCE(Identifier.ACCEPTANCE),
    TECHNICHIAN(Identifier.TECHNICHIAN);

    private String name;

    public class Identifier {
        public static final String ADMIN = "ADMIN";
        public static final String ACCEPTANCE = "ACCEPTANCE";

        public static final String TECHNICHIAN = "TECHNICHIAN";
    }

}
