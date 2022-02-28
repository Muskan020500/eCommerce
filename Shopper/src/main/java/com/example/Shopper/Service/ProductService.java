package com.example.Shopper.Service;

import com.example.Shopper.Entities.Product;
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
    public Product createProduct(String productDetails) throws JSONException {
        UUID productId = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(productDetails);
        String productName = jsonObject.getString("product_name");
        Double productPrice =Double.parseDouble(jsonObject.getString("product_price"));
        Product product = new Product(productId,productName,productPrice);
        productInterface.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
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

    @Override
    public Product getProduct(UUID productId) {
        Product productData = productInterface.findById(productId).get();
        return productData;
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productInterface.findAll();
    }
}
