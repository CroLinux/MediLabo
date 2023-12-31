package com.MediLabo.MSFrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.MediLabo.MSFrontend")
public class MsFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFrontendApplication.class, args);
	}

}
