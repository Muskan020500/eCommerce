package com.example.Shopper.Controller;

import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Entities.Product;
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
    public Customer createCustomer(@RequestBody String customerData)throws JSONException {
        Customer customer = customerServiceInterface.createCustomer(customerData);
        return customer;
    }
    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer){
        Customer customer = customerServiceInterface.updateCustomer(updatedCustomer);
       return customer;

    }
    @GetMapping("/get")
    public List<Customer> getCustomers(){
        return customerServiceInterface.getCustomers();
    }

    @GetMapping("/get/{customer_id}")
    public Customer getCustomer(@PathVariable UUID customer_id){
       return customerServiceInterface.getCustomer(customer_id);
    }
}
