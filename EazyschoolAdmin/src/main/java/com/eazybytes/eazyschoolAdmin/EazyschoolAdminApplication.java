package com.eazybytes.eazyschoolAdmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class EazyschoolAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyschoolAdminApplication.class, args);
	}

}
