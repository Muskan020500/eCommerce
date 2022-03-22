package com.example.Shopper.Controller;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.ProductInterface;
import com.example.Shopper.Service.ProductServiceInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @PostMapping("/create")
    public APIResponse createProduct(@RequestBody String productDetails) throws JSONException {
         return productServiceInterface.createProduct(productDetails);
    }
    @PutMapping("/update")
    public APIResponse updateProduct(@RequestBody Product updatedProduct) throws JSONException {
        return productServiceInterface.updateProduct(updatedProduct);


    }
    @GetMapping("/get/{productId}")
    public APIResponse getProduct(@PathVariable UUID productId){
        return productServiceInterface.getProduct(productId);
    }
    @GetMapping("/get")
    public APIResponse getProducts(){
       return productServiceInterface.getProducts();
    }
}
