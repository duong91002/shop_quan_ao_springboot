package com.example.shopquanao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopquanaoApplication {
        
	public static void main(String[] args) {
		SpringApplication.run(ShopquanaoApplication.class, args);
	}

}
