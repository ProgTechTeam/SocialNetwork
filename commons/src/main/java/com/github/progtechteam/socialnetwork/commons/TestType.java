package com.github.progtechteam.socialnetwork.commons;

/**
 * @author Evgenii Puliaev
 */
public enum TestType {
    TYPE_1(1),
    TYPE_2(2),
    OTHER(3),
    ;

    private final Integer id;

    TestType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Tries to parse given id into {@link TestType} instance.
     *
     * @param id id to find matching type
     * @return {@link TestType} with matching id or {@literal null}
     */
    public static TestType fromId(final Integer id) {
        if (id == null) {
            return null;
        }
        for (final var value : values()) {
            if (value.id.equals(id)) {
                return value;
            }
        }
        return null;
    }
}
