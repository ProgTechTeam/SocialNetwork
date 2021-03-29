package com.github.progtechteam.socialnetwork.api.handler;

import com.github.progtechteam.socialnetwork.services.model.error.ApiErrorBaseDto;
import com.github.progtechteam.socialnetwork.services.model.error.ApiErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author Evgenii Puliaev
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ConstraintViolationException.
     *
     * @param exception {@link ConstraintViolationException} exception object
     * @return {@link ResponseEntity} with {@link ApiErrorDto error dto}
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorDto<ApiErrorBaseDto>> handleConstraintViolation(ConstraintViolationException exception) {

        final var errorList = exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .map(ApiErrorBaseDto::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiErrorDto<>(errorList), HttpStatus.BAD_REQUEST);
    }
}
