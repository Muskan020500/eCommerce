package com.example.Shopper.Common;

import java.util.Map;

public class APIResponse {
    private int code;
    private String message;
    private String errorMessage;
    private Object data;
    private Status status;

    public APIResponse() {
    }

    public APIResponse(int code, String message, Object data, Status status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public APIResponse(int code, String errorMessage, Status status) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.status = status;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}

