package com.github.progtechteam.socialnetwork.security.model;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;

/**
 * Forms authentication response body.
 *
 * @author Evgenii Puliaev
 */
public interface AuthResponse {

    /**
     * Provides information about AuthUser.
     *
     * @return {@link CurrentUser} if authenticated, {@code null} otherwise
     */
    CurrentUser getCurrentUser();

}
