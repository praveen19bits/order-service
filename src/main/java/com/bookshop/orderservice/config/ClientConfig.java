package com.bookshop.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

	@Bean
	WebClient webClient(BookShopProperties clientProperties, WebClient.Builder webClientBuilder) {
		return webClientBuilder
				.baseUrl(clientProperties.catalogServiceUri().toString())
				.build();
	}

}
