package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.model.create.UserCreateDto;

/**
 * @author Evgenii Puliaev
 */
public interface UserService {

    CurrentUser create(UserCreateDto dto);

}
