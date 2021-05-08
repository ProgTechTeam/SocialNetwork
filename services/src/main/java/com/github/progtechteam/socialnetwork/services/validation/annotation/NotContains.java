package com.github.progtechteam.socialnetwork.services.validation.annotation;

import com.github.progtechteam.socialnetwork.services.validation.validator.NotContainsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Evgenii Puliaev
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {NotContainsValidator.class})
public @interface NotContains {

    /**
     * Substring that must not be a part of validated string.
     */
    String forbiddenString();

    /**
     * Message to present if validation fails.
     */
    String message();

    /**
     * Action groups before which validation needs to be performed.
     */
    Class<?>[] groups() default {};

    /**
     * Meta information used in validation.
     */
    Class<? extends Payload>[] payload() default {};
}
