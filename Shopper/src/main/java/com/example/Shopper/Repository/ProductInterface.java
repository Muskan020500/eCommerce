package com.example.Shopper.Repository;

import com.example.Shopper.Entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductInterface extends CrudRepository<Product, UUID> {
}
