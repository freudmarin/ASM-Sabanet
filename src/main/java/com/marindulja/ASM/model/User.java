package com.marindulja.ASM.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_detail")
public class User extends BaseEntity<Long> {

    @NonNull
    @Column(nullable = false, unique = true)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne()
    @JoinTable(name = "user_roles",
            joinColumns =
                    {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Role role;
}
