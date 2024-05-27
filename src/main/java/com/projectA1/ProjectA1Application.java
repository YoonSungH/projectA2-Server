package com.projectA1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectA1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectA1Application.class, args);
	}

}
