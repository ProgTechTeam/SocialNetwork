package com.github.progtechteam.socialnetwork.services.service;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;

/**
 * Service which can manually perform authentication by username and password.
 *
 * @author Evgenii Puliaev
 */
public interface AuthenticationService {

    CurrentUser authenticate(String username, String password);

}
