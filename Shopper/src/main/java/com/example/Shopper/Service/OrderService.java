package com.example.Shopper.Service;

import com.example.Shopper.Entities.MyOrder;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Repository.OrderInterface;
import com.example.Shopper.Repository.ProductInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements OrderServiceInterface{
    @Autowired
    OrderInterface orderInterface;
    @Autowired
    ProductInterface productInterface;

    @Override
    public MyOrder createOrder(String orderDetails) throws JSONException {
        UUID id = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(orderDetails);
        Double totalAmount = Double.parseDouble(jsonObject.getString("total_amount"));
        UUID customerId = UUID.fromString(jsonObject.getString("customer_id"));
        JSONArray jsonProductList = jsonObject.getJSONArray("product_list");
        List<UUID> productList = new ArrayList<>();
        if (jsonProductList != null) {
            for (int i=0;i<jsonProductList.length();i++){

                JSONObject productJsonObject = jsonProductList.getJSONObject(i);
                System.out.println(productJsonObject.getString("product_id"));
                productList.add(UUID.fromString(productJsonObject.getString("product_id")));
            }
        }
        MyOrder order = new MyOrder(id,totalAmount,customerId,productList);
        orderInterface.save(order);
        return order;
    }

    @Override
    public MyOrder updateOrder(MyOrder updatedOrder) {
        Optional<MyOrder> oldOrder = orderInterface.findById(updatedOrder.getOrder_id());
        if(oldOrder.isPresent()){
            MyOrder order = oldOrder.get();
            order.setCustomer_id(updatedOrder.getCustomer_id());
            order.setProduct_list(updatedOrder.getProduct_list());
            orderInterface.save(order);
            return order;
        }else {
            return null;
        }
    }

    @Override
    public MyOrder addProduct(String id, UUID order_id) {
        MyOrder order = orderInterface.findById(order_id).get();
        UUID product_id = UUID.fromString(id);
        if(order != null){
            List<UUID> newProductList= order.getProduct_list();
            newProductList.add(product_id);
            Double productPrice =  productInterface.findById(product_id).get().getProduct_price();
            order.setProduct_list(newProductList);
            order.setTotal_amount(order.getTotal_amount()+productPrice);
            orderInterface.save(order);
            return order;
        }
        else {
            return null;
        }
    }

    @Override
    public List<MyOrder> getOrders(UUID customer_id) {
        List<MyOrder> orderList = new ArrayList<>();
        Iterable<MyOrder> orders =  orderInterface.findAll();
        for(MyOrder order : orders){
            if (order.getCustomer_id().equals(customer_id)){
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public List<Product> getProduct(UUID order_id) {
        List<UUID> productIdList = orderInterface.findById(order_id).get().getProduct_list();
        List<Product> products = new ArrayList<>();
        for (UUID productId :productIdList){
            products.add(productInterface.findById(productId).get());
        }
        return products;
    }

    @Override
    public MyOrder getOrders() {
        return (MyOrder) orderInterface.findAll();
    }

    @Override
    public MyOrder getOrder(UUID order_id) {
        return orderInterface.findById(order_id).get();
    }
}
