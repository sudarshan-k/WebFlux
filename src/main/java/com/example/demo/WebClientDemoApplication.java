package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.UserResponse;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
public class WebClientDemoApplication {
	
	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient.builder().baseUrl("https://jsonplaceholder.typicode.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	@GetMapping("/getusers")
	public Flux<UserResponse> trackAllBooking() {
		Flux<UserResponse> response =  webClient.get().uri("/users").retrieve().bodyToFlux(UserResponse.class);
		return response;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebClientDemoApplication.class, args);
	}

}

//		WebClient client = WebClient.create();
		
//		Mono<ResponseEntity<Object>> response = client
//						.get()
//						.uri(url)
//						.header(TOKEN_HEADER_KEY,TOKEN)
//						.retrieve()
//						.toEntity(Object.class);
						//.bodyToMono(HttpResponse.class);
						//.exchangeToMono(response -> {
//				            if (response.statusCode().equals(HttpStatus.OK)) {
//				                return response.bodyToMono(Person.class);
//				            }
//				            else {
//				                // Turn to error
//				                return response.createException().flatMap(Mono::error);
//				            }
//				        });

