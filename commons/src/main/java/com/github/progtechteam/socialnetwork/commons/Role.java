package com.github.progtechteam.socialnetwork.commons;

/**
 * @author Evgenii Puliaev
 */
public enum Role {
    USER(1),
    MODERATOR(2),
    ;

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
