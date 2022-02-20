package com.example.Shopper.Controller;

import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.ProductInterface;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductInterface productInterface;
    //@RequestHeader String productName, @RequestHeader Double productPrice
    //@RequestAttribute String productName,@RequestAttribute Double productPrice
    @PostMapping("/createProduct")
    public Product createProduct(@RequestBody String productDetails) throws JSONException {
          UUID productId = UUID.randomUUID();
          JSONObject jsonObject = new JSONObject(productDetails);
          String productName = jsonObject.getString("product_name");
          Double productPrice =Double.parseDouble(jsonObject.getString("product_price"));
          Product product = new Product(productId,productName,productPrice);
         productInterface.save(product);
          return product;
    }
    @PutMapping("/updatedProduct")
    public Product updateProduct(@RequestBody Product updatedProduct) throws JSONException {
        Optional<Product> oldProductData = productInterface.findById(updatedProduct.getProduct_id());
        if (oldProductData.isPresent()) {
           Product product = oldProductData.get();
           product.setProduct_name(updatedProduct.getProduct_name());
           product.setProduct_price(updatedProduct.getProduct_price());
           productInterface.save(product);
           return  product;
        }
        else {
            return null;
        }
    }
    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable UUID productId){
        Optional<Product> productData = productInterface.findById(productId);
        return productData.get();
    }
    @GetMapping("/getProducts")
    public List<Product> getProducts(){
       return (List<Product>) productInterface.findAll();
    }
}
