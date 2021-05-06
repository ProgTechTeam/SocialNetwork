package com.github.progtechteam.socialnetwork.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.progtechteam.socialnetwork.security.model.AuthResponseFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Forms response with {@link AuthResponseFailure} if access denied.
 *
 * @author Evgenii Puliaev
 */
@RequiredArgsConstructor
@Component
public class SnAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try (final var writer = response.getWriter()) {
            mapper.writeValue(writer, new AuthResponseFailure("Access denied"));
        }
    }
}
