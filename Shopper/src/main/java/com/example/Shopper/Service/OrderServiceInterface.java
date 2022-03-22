package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.MyOrder;
import com.example.Shopper.Entities.Product;
import org.json.JSONException;

import java.util.List;
import java.util.UUID;

public interface OrderServiceInterface {
    public APIResponse createOrder(String orderDetails) throws JSONException;
    public APIResponse updateOrder(MyOrder updatedOrder);
    public APIResponse addProduct(String id, UUID order_id);
    public APIResponse getOrders(UUID customer_id);
    public APIResponse getProduct(UUID order_id);
    public APIResponse getOrders();
    public APIResponse getOrder(UUID order_id);
}
