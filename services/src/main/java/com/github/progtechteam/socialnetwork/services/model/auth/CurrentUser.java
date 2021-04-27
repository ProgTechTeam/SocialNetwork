package com.github.progtechteam.socialnetwork.services.model.auth;

import com.github.progtechteam.socialnetwork.commons.Role;
import com.github.progtechteam.socialnetwork.data.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Evgenii Puliaev
 */
@Getter
@NoArgsConstructor
public class CurrentUser {

    private Integer id;
    private String email;
    private Role role;

    public CurrentUser(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.role = account.getRole();
    }

}
