package com.github.progtechteam.socialnetwork.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.progtechteam.socialnetwork.security.model.AuthResponseFailure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forms response with {@link AuthResponseFailure} if authentication is failed.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (final var writer = response.getWriter()) {
            mapper.writeValue(writer, new AuthResponseFailure("Invalid username or password"));
            log.error("Failed login attempt: Invalid email or password");
        }
    }
}
