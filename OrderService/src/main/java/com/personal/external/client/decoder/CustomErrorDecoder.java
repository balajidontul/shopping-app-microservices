package com.personal.external.client.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.Exception.OrderServiceExceptionHandler;
import com.personal.external.client.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse;
        try {
            errorResponse = mapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            log.info(errorResponse);
            return new OrderServiceExceptionHandler(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
        } catch (IOException e) {
            throw new OrderServiceExceptionHandler("Internal Server Error","500");
        }
    }
}
