package com.github.progtechteam.socialnetwork.security.config;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
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
@Configuration
public class CurrentUserConfig {

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

        if (principal instanceof CurrentUser) {
            return (CurrentUser) principal;
        }

        throw new IllegalStateException("Unexpected principal type: " + principal.getClass());
    }

}
