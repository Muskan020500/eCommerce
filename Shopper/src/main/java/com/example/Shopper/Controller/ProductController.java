package com.example.Shopper.Controller;

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
    public Product createProduct(@RequestBody String productDetails) throws JSONException {
         Product product = productServiceInterface.createProduct(productDetails);
         return product;
    }
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product updatedProduct) throws JSONException {
        Product product = productServiceInterface.updateProduct(updatedProduct);
        return product;

    }
    @GetMapping("/get/{productId}")
    public Product getProduct(@PathVariable UUID productId){
        return productServiceInterface.getProduct(productId);
    }
    @GetMapping("/get")
    public List<Product> getProducts(){
       return productServiceInterface.getProducts();
    }
}
