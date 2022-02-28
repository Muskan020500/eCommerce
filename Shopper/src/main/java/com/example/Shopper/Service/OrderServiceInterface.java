package com.example.Shopper.Service;

import com.example.Shopper.Entities.MyOrder;
import com.example.Shopper.Entities.Product;
import org.json.JSONException;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {
    public MyOrder createOrder(String orderDetails) throws JSONException;
    public MyOrder updateOrder(MyOrder updatedOrder);
    public MyOrder addProduct(String id, UUID order_id);
    public List<MyOrder> getOrders(UUID customer_id);
    public List<Product> getProduct(UUID order_id);
    public MyOrder getOrders();
    public MyOrder getOrder(UUID order_id);
}
