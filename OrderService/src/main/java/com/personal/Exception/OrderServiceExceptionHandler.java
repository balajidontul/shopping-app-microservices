package com.personal.Exception;


import lombok.Data;

@Data
public class OrderServiceExceptionHandler extends RuntimeException {
    String errorCode;
    String errorMessage;


    public OrderServiceExceptionHandler(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
