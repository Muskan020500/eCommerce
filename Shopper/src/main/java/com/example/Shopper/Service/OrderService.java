package com.example.Shopper.Service;

import com.example.Shopper.Common.APIResponse;
import com.example.Shopper.Common.Status;
import com.example.Shopper.Entities.MyOrder;
import com.example.Shopper.Entities.Product;
import com.example.Shopper.Exception.CannotFindServiceException;
import com.example.Shopper.Exception.EmptyInputException;
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
    public APIResponse createOrder(String orderDetails) throws JSONException {
        APIResponse apiResponse = new APIResponse();
        UUID id = UUID.randomUUID();
        JSONObject jsonObject = new JSONObject(orderDetails);
        Double totalAmount = Double.parseDouble(jsonObject.getString("total_amount"));
        UUID customerId = UUID.fromString(jsonObject.getString("customer_id"));
        JSONArray jsonProductList = jsonObject.getJSONArray("product_list");
        List<UUID> productList = new ArrayList<>();
        if (jsonProductList == null) {
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Product list is empty");
            apiResponse.setStatus(Status.FAILURE);
            throw new EmptyInputException(apiResponse);
        }
            for (int i=0;i<jsonProductList.length();i++){

                JSONObject productJsonObject = jsonProductList.getJSONObject(i);
                System.out.println(productJsonObject.getString("product_id"));
                productList.add(UUID.fromString(productJsonObject.getString("product_id")));
            }

        MyOrder order = new MyOrder(id,totalAmount,customerId,productList);
        apiResponse.setCode(200);
        apiResponse.setMessage("Creating a new order");
        apiResponse.setData(order);
        apiResponse.setStatus(Status.SUCCESS);
        orderInterface.save(order);
        return apiResponse;
    }

    @Override
    public APIResponse updateOrder(MyOrder updatedOrder) {
        APIResponse apiResponse = new APIResponse();
        Optional<MyOrder> oldOrder = orderInterface.findById(updatedOrder.getOrder_id());
        if(!oldOrder.isPresent()){
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("Given order not present");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
            MyOrder order = oldOrder.get();
            order.setCustomer_id(updatedOrder.getCustomer_id());
            order.setProduct_list(updatedOrder.getProduct_list());
            orderInterface.save(order);
            apiResponse.setCode(200);
            apiResponse.setMessage("Updating the order");
            apiResponse.setData(order);
            apiResponse.setStatus(Status.SUCCESS);
            return apiResponse;

    }

    @Override
    public APIResponse addProduct(String id, UUID order_id) {
        APIResponse apiResponse = new APIResponse();
        MyOrder order = orderInterface.findById(order_id).get();
        UUID product_id = UUID.fromString(id);
        if(order == null){
            apiResponse.setCode(404);
            apiResponse.setErrorMessage("Given order not present");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
            List<UUID> newProductList= order.getProduct_list();
            newProductList.add(product_id);
            Double productPrice =  productInterface.findById(product_id).get().getProduct_price();
            order.setProduct_list(newProductList);
            order.setTotal_amount(order.getTotal_amount()+productPrice);
            orderInterface.save(order);
            apiResponse.setCode(200);
            apiResponse.setMessage("Adding product to the order");
            apiResponse.setData(order);
            apiResponse.setStatus(Status.SUCCESS);
            return apiResponse;


    }

    @Override
    public APIResponse getOrders(UUID customer_id) {
        APIResponse apiResponse = new APIResponse();
        List<MyOrder> orderList = new ArrayList<>();
        Iterable<MyOrder> orders =  orderInterface.findAll();
        for(MyOrder order : orders){
            if (order.getCustomer_id().equals(customer_id)){
                orderList.add(order);
            }
        }
        if(orderList.isEmpty()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("No order is present for given customer");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching Orders of particular customer");
        apiResponse.setData(orderList);
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }

    @Override
    public APIResponse getProduct(UUID order_id) {
        APIResponse apiResponse = new APIResponse();
        Optional<MyOrder> order = orderInterface.findById(order_id);
        if(!order.isPresent()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("Order is invalid");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        List<UUID> productIdList = order.get().getProduct_list();
        List<Product> products = new ArrayList<>();
        for (UUID productId :productIdList){
            products.add(productInterface.findById(productId).get());
        }
        if(products.isEmpty()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("No product is present for given order");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching Orders of particular customer");
        apiResponse.setData(products);
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }

    @Override
    public APIResponse getOrders() {
        APIResponse apiResponse = new APIResponse();
        MyOrder order = (MyOrder) orderInterface.findAll();
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching Orders ..");
        apiResponse.setData(order);
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }

    @Override
    public APIResponse getOrder(UUID order_id) {
        APIResponse apiResponse = new APIResponse();
        Optional<MyOrder> order = orderInterface.findById(order_id);
        if(!order.isPresent()){
            apiResponse.setCode(400);
            apiResponse.setErrorMessage("No order is present for given orderId");
            apiResponse.setStatus(Status.FAILURE);
            throw new CannotFindServiceException(apiResponse);
        }
        apiResponse.setCode(200);
        apiResponse.setMessage("Fetching Orders by id = "+order_id);
        apiResponse.setData(order.get());
        apiResponse.setStatus(Status.SUCCESS);
        return apiResponse;
    }
}
