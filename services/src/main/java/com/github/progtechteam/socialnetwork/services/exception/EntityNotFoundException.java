package com.github.progtechteam.socialnetwork.services.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Thrown when requested entity does not persist in database.
 *
 * @author Evgenii Puliaev
 */
@Getter
@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {

    private final String message;

}
