package br.com.unipac.apitrabalhos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ApiTrabalhosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTrabalhosApplication.class, args);
	}

}
