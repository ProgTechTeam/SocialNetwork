package com.github.progtechteam.socialnetwork.services.model.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Evgenii Puliaev
 */
@Schema(title = "ApiErrorDto", description = "Contains api errors")
@AllArgsConstructor
@Getter
public class ApiErrorDto <T extends ApiErrorBaseDto> {

    private final List<T> errors;

}
