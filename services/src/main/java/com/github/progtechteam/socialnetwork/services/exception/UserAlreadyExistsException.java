package com.github.progtechteam.socialnetwork.services.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Thrown when user attempts to register with data that should be unique.
 *
 * @author Evgenii Puliaev
 */
@Getter
@RequiredArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {

    private final String message;

}
