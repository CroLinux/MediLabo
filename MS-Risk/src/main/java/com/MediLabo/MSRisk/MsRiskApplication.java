package com.MediLabo.MSRisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.MediLabo.MSRisk")
public class MsRiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRiskApplication.class, args);
	}

}
