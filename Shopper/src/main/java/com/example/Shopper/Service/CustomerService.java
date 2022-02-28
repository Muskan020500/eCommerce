package com.example.Shopper.Service;

import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Repository.CustomerInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    CustomerInterface customerInterface;

    @Override
    public Customer createCustomer(String customerData) throws JSONException {
        UUID customerId = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(customerData);
        String customerName = jsonObject.getString("customer_name");
        String customerEmail  = jsonObject.getString("customer_email");
        Long customerPhone =Long.parseLong(jsonObject.getString("customer_phone"));
        Customer customer = new Customer(customerId,customerName,customerEmail,customerPhone);
        customerInterface.save(customer);
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
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

    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) customerInterface.findAll();
    }

    @Override
    public Customer getCustomer(UUID customer_id) {
        return customerInterface.findById(customer_id).get();
    }
}
