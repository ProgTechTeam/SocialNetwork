package com.github.progtechteam.socialnetwork.services.model.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Evgenii Puliaev
 */
@AllArgsConstructor
@Getter
@Schema(description = "Contains api error code")
public class ApiErrorBaseDto {

    private final String error;

}
