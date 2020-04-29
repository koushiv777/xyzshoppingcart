package com.xyz.shopping.online.shoppingautodiscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ShoppingAutodiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingAutodiscoveryServerApplication.class, args);
	}

}
