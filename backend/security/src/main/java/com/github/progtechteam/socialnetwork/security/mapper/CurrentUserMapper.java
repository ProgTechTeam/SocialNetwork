package com.github.progtechteam.socialnetwork.security.mapper;

import com.github.progtechteam.socialnetwork.security.model.AuthenticatedUserDetails;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import org.springframework.stereotype.Service;

/**
 * Maps to CurrentUser.
 *
 * @author Evgenii Puliaev
 */
@Service
public class CurrentUserMapper {

    public CurrentUser fromAuthenticatedUserDetails(AuthenticatedUserDetails details) {
        final var currUser = new CurrentUser();
        currUser.setId(details.getId());
        currUser.setEmail(details.getEmail());
        currUser.setRole(details.getRole());
        return currUser;
    }

}
