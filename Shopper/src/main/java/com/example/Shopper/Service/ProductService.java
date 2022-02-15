package com.example.Shopper.Service;

import com.example.Shopper.Entities.Product;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class ProductService {
    /*EntityManager entityManager;

    public ProductService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public long saveProduct(Product product){
        entityManager.persist(product);
        return product.getProduct_id();
    }*/
}
