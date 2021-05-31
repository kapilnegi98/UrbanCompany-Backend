package com.urbancompany.paymentservice;

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
@SpringBootApplication(scanBasePackages = {"com.urbancompany.commonservice", "com.urbancompany.paymentservice"})
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
