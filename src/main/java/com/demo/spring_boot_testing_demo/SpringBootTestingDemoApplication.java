package com.demo.spring_boot_testing_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootTestingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestingDemoApplication.class, args);
	}

}
