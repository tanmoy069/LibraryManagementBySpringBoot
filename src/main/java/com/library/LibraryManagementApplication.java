package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.library.*")
@SpringBootApplication
public class LibraryManagementApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

}
