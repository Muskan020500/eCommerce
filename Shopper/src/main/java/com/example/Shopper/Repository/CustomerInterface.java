package com.example.Shopper.Repository;

import com.example.Shopper.Entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerInterface extends CrudRepository<Customer, UUID> {

}
