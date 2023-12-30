package com.safestreets.services;

import java.util.List;

import com.safestreets.model.Order;
import com.safestreets.model.Product;
import com.safestreets.model.User;
import com.safestreets.model.repository.OrderRepository;

import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderService
 * Created By: coreydenning
 * Created On: 12/29/23
 **/
@Singleton
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order createNewOrder(User user, List<Product> products) {
    Order order = new Order().withUser(user).withProducts(products);
    log.info("Order creation success! Id: " + order.getId());
    return orderRepository.save(order);
  }
}
