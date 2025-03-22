package com.satya.postApp;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Tag(name = "Translation API", description = "REST APIs for text translation")

@SpringBootApplication
public class PostApplication {
// http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(PostApplication.class, args);
	}

}
