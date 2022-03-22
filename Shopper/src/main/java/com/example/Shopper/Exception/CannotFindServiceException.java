package com.example.Shopper.Exception;

import com.example.Shopper.Common.APIResponse;

public class CannotFindServiceException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
    private APIResponse apiResponse;

    public APIResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public CannotFindServiceException(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }

    public CannotFindServiceException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
}
