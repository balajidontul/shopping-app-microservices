package com.personal;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String errorCode, String errorMessage){
        super(errorMessage);
    }
}
