package com.example.Shopper.Controller;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Entities.MyOrder;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.OrderInterface;
import com.example.Shopper.Repository.ProductInterface;
import com.example.Shopper.Service.OrderServiceInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceInterface orderServiceInterface;

    @PostMapping("/create")
    public APIResponse createOrder(@RequestBody String orderDetails) throws JSONException {
        return orderServiceInterface.createOrder(orderDetails);

    }
    @PutMapping("/update")
    public APIResponse updateOrder(@RequestBody MyOrder updatedOrder){
        return orderServiceInterface.updateOrder(updatedOrder);

    }

    @GetMapping("/get/{order_id}")
    public APIResponse getOrder(@PathVariable UUID order_id){
        return orderServiceInterface.getOrder(order_id);
    }
    @PutMapping("/add/product/{order_id}")
    public APIResponse addProduct(@RequestBody String ID,@PathVariable UUID order_id){
       return orderServiceInterface.addProduct(ID,order_id);

    }
    @GetMapping("get/customerId/{customer_id}")
    public APIResponse getOrders(@PathVariable UUID customer_id){
        return orderServiceInterface.getOrders(customer_id);

    }
    @GetMapping("get/product/{order_id}")
    public APIResponse getProduct(@PathVariable UUID order_id){
        return orderServiceInterface.getProduct(order_id);

    }
}
