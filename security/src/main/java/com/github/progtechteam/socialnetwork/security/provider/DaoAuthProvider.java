package com.github.progtechteam.socialnetwork.security.provider;

import com.github.progtechteam.socialnetwork.security.service.CurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Sets CurrentUser as principal id authentication was success.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
public class DaoAuthProvider extends DaoAuthenticationProvider {

    private final CurrentUserService currentUserService;

    @Override
    protected Authentication createSuccessAuthentication(Object principal,
                                                         Authentication authentication,
                                                         UserDetails user) {
        final var token = new UsernamePasswordAuthenticationToken(
                currentUserService.loadByUsername(user.getUsername()),
                null,
                user.getAuthorities()
        );
        token.setDetails(authentication.getDetails());
        return token;
    }
}
