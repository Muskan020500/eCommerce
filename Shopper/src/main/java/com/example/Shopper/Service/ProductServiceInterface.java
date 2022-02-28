package com.example.Shopper.Service;

import com.example.Shopper.Entities.Product;
import org.json.JSONException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductServiceInterface {
   public Product createProduct(String productDetails) throws JSONException;
   public Product updateProduct(Product updatedProduct);
   public Product getProduct(UUID productId);
   public List<Product> getProducts();
}
