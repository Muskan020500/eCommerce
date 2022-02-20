package com.example.Shopper.Repository;

import com.example.Shopper.Entities.MyOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderInterface extends CrudRepository<MyOrder, UUID> {
}
