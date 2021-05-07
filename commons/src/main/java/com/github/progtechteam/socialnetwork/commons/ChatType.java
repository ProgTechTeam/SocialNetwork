package com.github.progtechteam.socialnetwork.commons;

/**
 * @author Evgenii Puliaev
 */
public enum ChatType {
    PRIVATE_CHAT(1),
    GROUP_CHAT(2),
    ;

    private final int id;

    ChatType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * Tries to parse given id into {@link ChatType} instance.
     *
     * @param id id to find matching type
     * @return {@link ChatType} with matching id or {@literal null}
     */
    public static ChatType fromId(final Integer id) {
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
