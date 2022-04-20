package com.example.Shopper.Common;



import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "shopper")
public class CustomEndpoint {
    @ReadOperation
    public String customEndPointByName() {
        return "Lets Shop";
    }
}
