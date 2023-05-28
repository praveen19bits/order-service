package com.bookshop.orderservice.order.domain;

import com.bookshop.orderservice.book.BookClient;
import com.bookshop.orderservice.book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final BookClient bookClient;
	private final OrderRepository orderRepository;

	public OrderService(BookClient bookClient, OrderRepository orderRepository) {
		this.bookClient = bookClient;
		this.orderRepository = orderRepository;
	}

	public Flux<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Mono<Order> submitOrder(String isbn, int quantity) {
		return bookClient.getBookByIsbn(isbn)
				.map(book -> buildAcceptedOrder(book, quantity))
				.defaultIfEmpty(buildRejectedOrder(isbn, quantity))
				.flatMap(orderRepository::save);
	}

	public static Order buildAcceptedOrder(Book book, int quantity) {
		return Order.of(book.isbn(), book.title() + " - " + book.author(),
				book.price(), quantity, OrderStatus.ACCEPTED, "Order Accepted Successfully.");
	}

	public static Order buildRejectedOrder(String bookIsbn, int quantity) {
		return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED, "Seems there are no books available with this ISBN, Please try after some time.");
	}

}
