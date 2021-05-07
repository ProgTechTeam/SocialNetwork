package com.github.progtechteam.socialnetwork.services.model.auth;

import com.github.progtechteam.socialnetwork.commons.Role;
import lombok.Data;

/**
 * Represents current user logged in system.
 *
 * @author Evgenii Puliaev
 */
@Data
public class CurrentUser {

    private Integer id;
    private String email;
    private Role role;

}
