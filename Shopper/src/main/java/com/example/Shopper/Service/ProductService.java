package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Common.Status;
import com.example.Shopper.Entities.Customer;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Exception.CannotFindServiceException;
import com.example.Shopper.Exception.EmptyInputException;
import com.example.Shopper.Repository.ProductInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements ProductServiceInterface{
    @Autowired
    ProductInterface productInterface;

    @Override
    public APIResponse createProduct(String productDetails) throws JSONException {
        APIResponse apiResponse = new APIResponse();
        UUID productId = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(productDetails);
        String productName = jsonObject.getString("product_name");
        Double productPrice =Double.parseDouble(jsonObject.getString("product_price"));
        Product product = new Product(productId,productName,productPrice);

        if(product.getProduct_name().isEmpty() || product.getProduct_price() == null){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Input Fields are empty");
            apiResponse.setStatus(Status.FAILURE);
            throw new EmptyInputException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Creating new Product");
        apiResponse.setData(product);
        apiResponse.setStatus(Status.SUCCESS);
        productInterface.save(product);
        return apiResponse;
    }

    @Override
    public APIResponse updateProduct(Product updatedProduct) {
        APIResponse apiResponse = new APIResponse();
        Optional<Product> oldProductData = productInterface.findById(updatedProduct.getProduct_id());
        if (!oldProductData.isPresent()) {
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("Given Product is not present");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
            Product product = oldProductData.get();
            product.setProduct_name(updatedProduct.getProduct_name());
            product.setProduct_price(updatedProduct.getProduct_price());
            apiResponse.setCode(200);
            apiResponse.setMessage("Updating the data");
            apiResponse.setData(product);
            apiResponse.setStatus(Status.SUCCESS);
            productInterface.save(product);
            return apiResponse;


    }

    @Override
    public APIResponse getProduct(UUID productId) {
        APIResponse apiResponse = new APIResponse();
        Optional<Product> product = productInterface.findById(productId);
        if (!product.isPresent()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Given id not present");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching the product of id = "+productId);
        apiResponse.setData(product.get());
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;

    }

    @Override
    public APIResponse getProducts() {
        APIResponse apiResponse = new APIResponse();
        List<Product> productList =  (List<Product>) productInterface.findAll();
        if(productList.isEmpty()){
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("List is Empty");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching the list of products");
        apiResponse.setData(productList);
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }
}
