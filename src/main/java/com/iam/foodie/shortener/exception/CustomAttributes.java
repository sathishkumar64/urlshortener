package com.iam.foodie.shortener.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.rmi.ServerException;
import java.util.Map;

/**
 * @author satvasu
 */
@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request,options);

        Throwable throwable=getError(request);



        if (getError(request) instanceof GlobalException) {
            GlobalException ex = (GlobalException) getError(request);
            errorAttributes.put("message",ex.getMessage());
            errorAttributes.put("status", ex.getStatus().value());
            errorAttributes.put("error", ex.getStatus().getReasonPhrase());
            return errorAttributes;
        }else if (throwable instanceof ServerException){
            errorAttributes.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
            errorAttributes.put("data", "INTERNAL SERVER ERROR");
            errorAttributes.put("message",throwable.getMessage());
        }
        else if (throwable instanceof Exception){
            errorAttributes.put("message",throwable.getLocalizedMessage());
        }



      /*  if(throwable instanceof ResponseStatusException){
            ResponseStatusException ex=(ResponseStatusException) throwable;
            errorAttributes.put("message",ex.getMessage());
            return  errorAttributes;
        }*/
        return errorAttributes;
    }
}
