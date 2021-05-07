package com.github.progtechteam.socialnetwork.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.progtechteam.socialnetwork.security.model.AuthResponseFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forms response with {@link AuthResponseFailure} if user is not authenticated.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (response.getStatus() != HttpServletResponse.SC_FORBIDDEN) {
            response.setCharacterEncoding(request.getCharacterEncoding());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try (final var writer = response.getWriter()) {
                mapper.writeValue(writer, new AuthResponseFailure("Unauthorized"));
            }
        }
    }
}
