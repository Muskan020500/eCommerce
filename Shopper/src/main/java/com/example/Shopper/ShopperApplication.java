package com.example.Shopper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
public class ShopperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopperApplication.class, args);
	}

}
