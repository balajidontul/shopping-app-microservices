package com.personal.Exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{


    private String errorCode;
    private String errorMessage;

    public ProductServiceCustomException(String Message, String errorCode){
        super(Message);
        this.errorCode = errorCode;
        this.errorMessage = Message;
    }
}
