package com.github.progtechteam.socialnetwork.commons;

/**
 * Represents common urls.
 *
 * @author Evgenii Puliaev
 */
public final class Url {

    public static final String PUBLIC = "/public";

    public static final String AUTH = "/auth";
    public static final String LOGIN = "/auth/login";
    public static final String LOGOUT = "/auth/logout";

    public static final String MODERATOR = "/moderator";

    public static final String SWAGGER_UI = "/swagger-ui";
    public static final String API_DOCS = "/api-docs";

    public static final String WILDCARD = "/**";

    private Url() {
        // private constructor to hide the implicit public one
    }
}
