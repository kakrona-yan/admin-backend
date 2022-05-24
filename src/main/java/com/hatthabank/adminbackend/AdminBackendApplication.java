package com.hatthabank.adminbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hatthabank.sdk.configuration.annoatation.EnableHTBFeignClient;
import com.hatthabank.sdk.configuration.annoatation.EnableHTBScan;

@SpringBootApplication
@EnableAutoConfiguration
@EnableHTBScan
@EnableHTBFeignClient
public class AdminBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminBackendApplication.class, args);
	}

}
