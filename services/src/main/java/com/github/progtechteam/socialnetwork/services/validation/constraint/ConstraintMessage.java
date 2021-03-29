package com.github.progtechteam.socialnetwork.services.validation.constraint;

/**
 * @author Evgenii Puliaev
 */
public final class ConstraintMessage {

    private ConstraintMessage() {
        // private constructor to hide the implicit public one
    }

    public static class Field {

        public static final String TEST_CONTENT = "TEST_CONTENT";

        private Field() {
            // private constructor to hide the implicit public one
        }
    }

    public static class Constraint {

        public static final String IS_NULL = "_NULL";
        public static final String IS_EMPTY = "_EMPTY";
        public static final String TOO_LONG = "_TOO_LONG";
        public static final String TOO_SHORT = "_TOO_SHORT";
        public static final String UNDER_MIN = "_UNDER_MIN";
        public static final String UPPER_MAX = "_UPPER_MAX";
        public static final String INCORRECT = "_INCORRECT";

        private Constraint() {
            // private constructor to hide the implicit public one
        }
    }

}
