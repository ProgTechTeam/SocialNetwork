package com.github.progtechteam.socialnetwork.security.config;

import com.github.progtechteam.socialnetwork.security.mapper.CurrentUserMapper;
import com.github.progtechteam.socialnetwork.security.model.AuthenticatedUserDetails;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

/**
 * Configuration responsible for providing current user to services.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Configuration
public class CurrentUserConfig {

    private final CurrentUserMapper mapper;

    /**
     * Retrieves current user for current request.
     *
     * @return {@link CurrentUser}
     */
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    CurrentUser currentUser() {
        final Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal == null) {
            throw new IllegalStateException("Current user must be set");
        }

        if (principal instanceof AuthenticatedUserDetails) {
            return mapper.fromAuthenticatedUserDetails((AuthenticatedUserDetails) principal);
        }

        throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
    }

}
