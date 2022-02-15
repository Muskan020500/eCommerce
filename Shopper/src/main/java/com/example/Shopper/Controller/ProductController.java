package com.example.Shopper.Controller;

import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductInterface productInterface;
    @PostMapping("/createProduct")
    public Product createProduct(@RequestHeader String productName, @RequestHeader Double productPrice){
          UUID productId = UUID.randomUUID();
          Product product = new Product(productId,productName,productPrice);
          productInterface.save(product);
          return product;
    }
}
