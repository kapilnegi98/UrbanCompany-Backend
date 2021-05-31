package com.uc.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class UcDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UcDiscoveryServerApplication.class, args);
	}

}
