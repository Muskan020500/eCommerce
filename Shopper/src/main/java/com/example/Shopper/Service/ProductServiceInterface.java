package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.Product;
import org.json.JSONException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductServiceInterface {
   public APIResponse createProduct(String productDetails) throws JSONException;
   public APIResponse updateProduct(Product updatedProduct);
   public APIResponse getProduct(UUID productId);
   public APIResponse getProducts();
}
