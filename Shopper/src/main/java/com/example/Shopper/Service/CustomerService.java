package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Common.Status;
import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Exception.CannotFindCustomerException;
import com.example.Shopper.Exception.CannotFindServiceException;
import com.example.Shopper.Exception.EmptyInputException;
import com.example.Shopper.Repository.CustomerInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements CustomerServiceInterface{
    @Autowired
    CustomerInterface customerInterface;

    @Override
    public APIResponse createCustomer(String customerData) throws JSONException ,EmptyInputException{
        APIResponse apiResponse = new APIResponse();
        UUID customerId = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(customerData);
        String customerName = jsonObject.getString("customer_name");
        String customerEmail  = jsonObject.getString("customer_email");
        Long customerPhone =Long.parseLong(jsonObject.getString("customer_phone"));
        Customer customer = new Customer(customerId,customerName,customerEmail,customerPhone);
        if(customerName.isEmpty() || customerEmail.isEmpty() || customerPhone == null){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Input Fields are empty");
            apiResponse.setStatus(Status.FAILURE);
            throw new EmptyInputException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Creating new customer");
        apiResponse.setData(customer);
        apiResponse.setStatus(Status.SUCCESS);
        customerInterface.save(customer);
        return apiResponse;
    }

    @Override
    public APIResponse updateCustomer(Customer updatedCustomer) throws CannotFindServiceException {
        APIResponse apiResponse = new APIResponse();
        Optional<Customer> oldCustomer = customerInterface.findById(updatedCustomer.getCustomer_id());

        if (!oldCustomer.isPresent()){
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("Given Customer not present");
            apiResponse.setStatus(Status.FAILURE);
           throw new CannotFindServiceException(apiResponse);
        }

            Customer customer = oldCustomer.get();
            customer.setCustomer_name(updatedCustomer.getCustomer_name());
            customer.setCustomer_email(updatedCustomer.getCustomer_email());
            customer.setCustomer_phone(updatedCustomer.getCustomer_phone());
            apiResponse.setCode(200);
            apiResponse.setMessage("Updating the data");
            apiResponse.setData(customer);
            apiResponse.setStatus(Status.SUCCESS);
            customerInterface.save(customer);
            return apiResponse;

    }

    @Override
    public APIResponse getCustomers() throws CannotFindServiceException {
        APIResponse apiResponse = new APIResponse();
        List<Customer> customers =  (List<Customer>) customerInterface.findAll();
        if(customers.isEmpty()){
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("List is Empty");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching the list of customers");
        apiResponse.setData(customers);
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }

    @Override
    public APIResponse getCustomer(UUID customer_id) throws CannotFindServiceException{
        APIResponse apiResponse = new APIResponse();
        Optional<Customer> customer = customerInterface.findById(customer_id);
        if(!customer.isPresent()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Given id not present");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching the Customer of id = "+customer_id );
        apiResponse.setData(customer.get());
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }
}
