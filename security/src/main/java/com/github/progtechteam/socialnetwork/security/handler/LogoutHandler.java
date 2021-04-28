package com.github.progtechteam.socialnetwork.security.handler;

import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class LogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) {
        response.setStatus(HttpServletResponse.SC_OK);
        log.info("User=[{}] successfully logged out", ((CurrentUser) authentication.getPrincipal()).getId());
    }
}
