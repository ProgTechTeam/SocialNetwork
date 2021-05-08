package com.github.progtechteam.socialnetwork.security.service.impl;

import com.github.progtechteam.socialnetwork.security.mapper.CurrentUserMapper;
import com.github.progtechteam.socialnetwork.security.model.AuthenticatedUserDetails;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.services.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final CurrentUserMapper mapper;

    @Override
    public CurrentUser authenticate(String username, String password) {
        final var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return mapper.fromAuthenticatedUserDetails((AuthenticatedUserDetails) authentication.getPrincipal());
    }

}
