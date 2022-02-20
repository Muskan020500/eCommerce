package com.example.Shopper.Controller;

import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.CustomerInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CustomerController {
    @Autowired
    CustomerInterface customerInterface;

    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody String customerData)throws JSONException {
        UUID customerId = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(customerData);
        String customerName = jsonObject.getString("customer_name");
        String customerEmail  = jsonObject.getString("customer_email");
        Long customerPhone =Long.parseLong(jsonObject.getString("customer_phone"));
        Customer customer = new Customer(customerId,customerName,customerEmail,customerPhone);
        customerInterface.save(customer);
        return customer;
    }
    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer updatedCustomer){
        Optional<Customer> oldCustomer = customerInterface.findById(updatedCustomer.getCustomer_id());
        if (oldCustomer.isPresent()){
            Customer customer = oldCustomer.get();
            customer.setCustomer_name(updatedCustomer.getCustomer_name());
            customer.setCustomer_email(updatedCustomer.getCustomer_email());
            customer.setCustomer_phone(updatedCustomer.getCustomer_phone());
            customerInterface.save(customer);
            return customer;
        }
        else {
            return null;
        }
    }
    @GetMapping("/getCustomers")
    public List<Customer> getCustomers(){
        return (List<Customer>) customerInterface.findAll();
    }
    @GetMapping("/getCustomer/{customer_id}")
    public Customer getCustomer(@PathVariable UUID customer_id){
        return customerInterface.findById(customer_id).get();
    }
}
