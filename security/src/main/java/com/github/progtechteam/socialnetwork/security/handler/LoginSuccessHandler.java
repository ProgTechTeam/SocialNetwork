package com.github.progtechteam.socialnetwork.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.progtechteam.socialnetwork.services.model.auth.CurrentUser;
import com.github.progtechteam.socialnetwork.security.model.AuthResponseFailure;
import com.github.progtechteam.socialnetwork.security.model.AuthResponseSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forms response with {@link AuthResponseSuccess} if authentication is successful.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final var currentUser = (CurrentUser) authentication.getPrincipal();
        try (final var writer = response.getWriter()) {
            if (currentUser == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                mapper.writeValue(writer, new AuthResponseFailure("Principal is null"));
                log.error("Failed login attempt: Principal is null");
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(writer, new AuthResponseSuccess(currentUser));
                log.info("User=[{}] successfully logged in", currentUser.getId());
            }
        }
    }
}
