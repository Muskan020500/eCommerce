package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Exception.CannotFindCustomerException;
import com.example.Shopper.Exception.CannotFindServiceException;
import org.json.JSONException;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

   public APIResponse createCustomer(String customerData) throws JSONException;
   public APIResponse updateCustomer(Customer updatedCustomer) throws CannotFindServiceException;
   public APIResponse getCustomers() throws CannotFindServiceException;
   public APIResponse getCustomer(UUID customer_id) throws CannotFindServiceException;
}
