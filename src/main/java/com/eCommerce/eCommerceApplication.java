package com.eCommerce;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;





@SpringBootApplication
@EnableScheduling
public class eCommerceApplication implements CommandLineRunner{
	

	
	public static void main(String[] args) {
		SpringApplication.run(eCommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	

}
