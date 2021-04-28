package com.github.progtechteam.socialnetwork.security.model;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import lombok.RequiredArgsConstructor;

/**
 * Used as authentication response body for successful authentication.
 * Contains {@link CurrentUser}.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
public class AuthResponseSuccess implements AuthResponse {

    private final CurrentUser currentUser;

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrentUser getCurrentUser() {
        return currentUser;
    }
}
