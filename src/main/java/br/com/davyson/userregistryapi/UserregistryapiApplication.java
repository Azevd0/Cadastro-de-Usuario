package br.com.davyson.userregistryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UserregistryapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserregistryapiApplication.class, args);
	}

}
