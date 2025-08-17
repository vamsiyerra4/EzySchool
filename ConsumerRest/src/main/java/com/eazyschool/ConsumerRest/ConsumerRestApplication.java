package com.eazyschool.ConsumerRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.eazyschool.ConsumerRest.proxy")
public class ConsumerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerRestApplication.class, args);
	}

}
