package com.bookshop.orderservice.order.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public record Order (

	@Id
	Long id,

	String bookIsbn,
	String bookName,
	Double bookPrice,
	Integer quantity,
	OrderStatus status,

	String message,

	@CreatedDate
	Instant createdDate,

	@LastModifiedDate
	Instant lastModifiedDate,

	@Version
	int version
){

	public static Order of(String bookIsbn, String bookName, Double bookPrice, Integer quantity, OrderStatus status, String message) {
		return new Order(null, bookIsbn, bookName, bookPrice, quantity, status, message,null, null, 0);
	}

}
