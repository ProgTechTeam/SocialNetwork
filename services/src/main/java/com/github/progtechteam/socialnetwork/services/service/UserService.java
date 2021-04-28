package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;

/**
 * @author Evgenii Puliaev
 */
public interface UserService {

    void create(UserCreateDto dto);

}
