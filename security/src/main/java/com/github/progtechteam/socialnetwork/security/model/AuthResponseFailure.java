package com.github.progtechteam.socialnetwork.security.model;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Used as authentication response body for failed authentication.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Getter
public class AuthResponseFailure implements AuthResponse {

    private final String errorMessage;

    /**
     * Returns {@code null}.
     */
    @Override
    public CurrentUser getCurrentUser() {
        return null;
    }
}
