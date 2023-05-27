package com.bookshop.orderservice.config;

import java.net.URI;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bookshop")
public record BookShopProperties(

	@NotNull
	URI catalogServiceUri

){}
