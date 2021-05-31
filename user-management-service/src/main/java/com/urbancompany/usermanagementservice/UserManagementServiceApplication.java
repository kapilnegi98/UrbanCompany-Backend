package com.urbancompany.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;


@EnableDiscoveryClient
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:application-common.properties")
})
@SpringBootApplication(scanBasePackages = {"com.urbancompany.commonservice", "com.urbancompany.usermanagementservice"})
public class UserManagementServiceApplication {

	public static void main(String[] args) {
		System.out.println("------start");
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}
