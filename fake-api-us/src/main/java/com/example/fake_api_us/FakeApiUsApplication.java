package com.example.fake_api_us;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FakeApiUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeApiUsApplication.class, args);
	}

}
