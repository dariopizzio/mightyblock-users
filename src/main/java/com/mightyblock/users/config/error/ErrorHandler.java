package com.mightyblock.users.config.error;

import com.mightyblock.users.dto.ApiErrorDto;
import com.mightyblock.users.model.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    /**
     * Handle all the ApiExceptions threw by the application
     * @param exception exception handled (ApiException)
     * @return ApiErrorDto
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorDto> handleException(Exception exception) {
        ApiException apiException = (ApiException) exception;
        return new ResponseEntity<>(apiException.getApiError(), apiException.getStatus());
    }
}
