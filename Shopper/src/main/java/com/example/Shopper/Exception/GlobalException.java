package com.example.Shopper.Exception;

import com.example.Shopper.Common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
        APIResponse apiResponse = emptyInputException.getApiResponse();
        return new ResponseEntity<String>(apiResponse.getErrorMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CannotFindServiceException.class)
    public ResponseEntity<APIResponse> handleCannotFind(CannotFindServiceException cannotFindServiceException){
        APIResponse apiResponse = cannotFindServiceException.getApiResponse();
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
