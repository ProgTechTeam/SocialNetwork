package com.github.progtechteam.socialnetwork.services.validation.validator;

import com.github.progtechteam.socialnetwork.services.validation.annotation.NotContains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Evgenii Puliaev
 */
public class NotContainsValidator implements ConstraintValidator<NotContains, String> {

    private String forbiddenString;

    @Override
    public void initialize(NotContains constraintAnnotation) {
        forbiddenString = constraintAnnotation.forbiddenString();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !value.contains(forbiddenString);
    }
}
