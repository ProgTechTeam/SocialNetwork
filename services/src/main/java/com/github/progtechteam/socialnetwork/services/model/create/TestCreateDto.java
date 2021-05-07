package com.github.progtechteam.socialnetwork.services.model.create;

import com.github.progtechteam.socialnetwork.services.validation.annotation.NotContains;
import com.github.progtechteam.socialnetwork.services.validation.constraint.ConstraintMessage.Constraint;
import com.github.progtechteam.socialnetwork.services.validation.constraint.ConstraintMessage.Field;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Evgenii Puliaev
 */
@Data
@Schema(description = "Contains only required data to create new test instance")
public class TestCreateDto {

    @Size(max = 10, message = Field.TEST_CONTENT + Constraint.TOO_LONG)
    @NotBlank(message = Field.TEST_CONTENT + Constraint.IS_EMPTY)
    @NotContains(forbiddenString = "test", message = Field.TEST_CONTENT + Constraint.INCORRECT)
    private String content;

}
