package com.example.Shopper.Controller;

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
    public MyOrder createOrder(@RequestBody String orderDetails) throws JSONException {
        MyOrder order = orderServiceInterface.createOrder(orderDetails);
        return order;

    }
    @PutMapping("/update")
    public MyOrder updateOrder(@RequestBody MyOrder updatedOrder){
        MyOrder order = orderServiceInterface.updateOrder(updatedOrder);
        return order;

    }
    @GetMapping ("/get")
    public MyOrder getOrders(){
       return orderServiceInterface.getOrders();
    }
    @GetMapping("/get/{order_id}")
    public MyOrder getOrder(@PathVariable UUID order_id){
        return orderServiceInterface.getOrder(order_id);
    }
    @PutMapping("/add/product/{order_id}")
    public MyOrder addProduct(@RequestBody String ID,@PathVariable UUID order_id){
        MyOrder order = orderServiceInterface.addProduct(ID,order_id);
        return order;
    }
    @GetMapping("get/customerId/{customer_id}")
    public List<MyOrder> getOrders(@PathVariable UUID customer_id){
        List<MyOrder> orderList = orderServiceInterface.getOrders(customer_id);
        return orderList;
    }
    @GetMapping("get/product/{order_id}")
    public List<Product> getProduct(@PathVariable UUID order_id){
        List<Product> products = orderServiceInterface.getProduct(order_id);
        return products;
    }
}
