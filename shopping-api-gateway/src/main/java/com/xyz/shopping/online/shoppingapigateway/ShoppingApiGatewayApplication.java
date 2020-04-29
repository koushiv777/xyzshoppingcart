package com.xyz.shopping.online.shoppingapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingApiGatewayApplication.class, args);
	}

}
