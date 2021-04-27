package com.github.progtechteam.socialnetwork.commons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents roles used in system.
 *
 * @author Evgenii Puliaev
 */
@Getter
@RequiredArgsConstructor
public enum Role {
    USER(1, "ROLE_USER"),
    MODERATOR(2, "ROLE_MODERATOR"),
    ;

    private final int id;
    private final String systemName;

    /**
     * Tries to parse given id into {@link Role} instance.
     *
     * @param id id to find matching type
     * @return {@link Role} with matching id or {@literal null}
     */
    public static Role fromId(final Integer id) {
        if (id == null) {
            return null;
        }
        for (final var value : values()) {
            if (id.equals(value.id)) {
                return value;
            }
        }
        return null;
    }
}
