package com.github.progtechteam.socialnetwork.services.model.create;

import lombok.Data;

/**
 * @author Evgenii Puliaev
 */
@Data
public class UserCreateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
