package com.example.Shopper.Controller;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Exception.CannotFindCustomerException;
import com.example.Shopper.Exception.CannotFindServiceException;
import com.example.Shopper.Exception.EmptyInputException;
import com.example.Shopper.Repository.CustomerInterface;
import com.example.Shopper.Service.CustomerServiceInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;
    @PostMapping("/create")
    public APIResponse createCustomer(@RequestBody String customerData)throws JSONException ,EmptyInputException{
        return customerServiceInterface.createCustomer(customerData);

    }
    @PutMapping("/update")
    public APIResponse updateCustomer(@RequestBody Customer updatedCustomer) throws CannotFindServiceException {
        return customerServiceInterface.updateCustomer(updatedCustomer);
    }
    @GetMapping("/get")
    public APIResponse getCustomers() throws CannotFindServiceException {
        return customerServiceInterface.getCustomers();
    }

    @GetMapping("/get/{customer_id}")
    public APIResponse getCustomer(@PathVariable UUID customer_id)throws CannotFindServiceException{
       return customerServiceInterface.getCustomer(customer_id);
    }
}
