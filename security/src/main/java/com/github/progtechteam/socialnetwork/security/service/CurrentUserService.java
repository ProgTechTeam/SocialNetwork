package com.github.progtechteam.socialnetwork.security.service;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;

/**
 * Retrieves current user.
 *
 * @author Evgenii Puliaev
 */
public interface CurrentUserService {

    CurrentUser loadByUsername(String username);

}
