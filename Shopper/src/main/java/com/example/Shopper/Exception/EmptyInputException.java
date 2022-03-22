package com.example.Shopper.Exception;

import com.example.Shopper.Common.APIResponse;
import org.springframework.stereotype.Component;

@Component
public class EmptyInputException extends RuntimeException{


    private APIResponse apiResponse;

    public APIResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }



    public EmptyInputException(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public EmptyInputException() {
    }


}
