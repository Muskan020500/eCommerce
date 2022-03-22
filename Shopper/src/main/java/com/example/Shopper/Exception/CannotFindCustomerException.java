package com.example.Shopper.Exception;

import com.example.Shopper.Common.APIResponse;

public class CannotFindCustomerException extends Exception{
    public CannotFindCustomerException(APIResponse message) {
        super(String.valueOf(message));
    }
}
