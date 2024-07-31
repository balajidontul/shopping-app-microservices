package com.personal.Exception;

import com.personal.external.client.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseErrorhandler {

    @ExceptionHandler(OrderServiceExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleException(OrderServiceExceptionHandler orderServiceExceptionHandler) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(orderServiceExceptionHandler.errorCode)
                .errorMessage(orderServiceExceptionHandler.getErrorMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
