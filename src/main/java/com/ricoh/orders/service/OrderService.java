package com.ricoh.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ricoh.orders.model.Associations;
import com.ricoh.orders.model.Order;


@Component
@Transactional
public class OrderService  {
	
	@Autowired
	private OrderRepository orderRepository;

	
	@Transactional()
	public Order create(Order order) {
		return orderRepository.save(order);
	}

	
	public Order read(Long id) {
		return orderRepository.findOne(id);
	}

	
	@Transactional()
	public Order update(Long id, Order order) {
		Order toUpdate = orderRepository.findOne(id);
		toUpdate.getArticles().forEach(article -> Associations.Has.unlink(toUpdate, article)); // Unlink actual articles
		order.getArticles().forEach(article -> Associations.Has.link(toUpdate, article)); // Link new articles
		return orderRepository.save(toUpdate);
	}

	
	@Transactional()
	public Order delete(Long id) {
		Order order = orderRepository.findOne(id);
		if (order != null) orderRepository.delete(id);
		return order;
	}

}
