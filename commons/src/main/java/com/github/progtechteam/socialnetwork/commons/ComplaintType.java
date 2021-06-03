package com.github.progtechteam.socialnetwork.commons;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Burdyug Pavel
 */
@Getter
@RequiredArgsConstructor
public enum ComplaintType {
    FRAUD(1, "Мошенничество"),
    SPAM(2, "Спам"),
    INSULT(3, "Оскорбление"),
    CD(4, "Детская порнография"),
    ;

    private final int id;
    private final String description;

    /**
     * Tries to parse given id into {@link ComplaintType} instance.
     *
     * @param id id to find matching type
     * @return {@link ComplaintType} with matching id or {@literal null}
     */
    public static ComplaintType fromId(final Integer id) {
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
