package com.project.allclear_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AllclearUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllclearUserApplication.class, args);
	}

}
