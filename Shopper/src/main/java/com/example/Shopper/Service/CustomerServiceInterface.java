package com.example.Shopper.Service;

import com.example.Shopper.Entities.Customer;
import org.json.JSONException;

import java.util.List;
import java.util.UUID;

public interface CustomerServiceInterface {

   public Customer createCustomer(String customerData) throws JSONException;
   public Customer updateCustomer(Customer updatedCustomer);
   public List<Customer> getCustomers();
   public Customer getCustomer(UUID customer_id);
}
