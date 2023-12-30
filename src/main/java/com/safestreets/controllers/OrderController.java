package com.safestreets.controllers;

import java.util.List;

import com.safestreets.model.Order;
import com.safestreets.model.User;
import com.safestreets.model.repository.UserRepository;
import com.safestreets.model.repository.OrderRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

// For simplicity, pagination is not being used.
@Controller("/order")
public class OrderController {

  @Inject OrderRepository orderRepo;

  // Return complete list of orders
  @Get
  public List<Order> getOrders(){
    return orderRepo.findAll();
  }
  
  // Return specific Order based on its ID
  @Get("/{id}")
  public HttpResponse<Order> getOrder(Long id){
    Order order = orderRepo.findById(id).orElse(null);
    return HttpResponse.ok(order);
  }

  // Return list of Orders related to a specifc User's ID
  @Get("/user/{id}")
  public HttpResponse<List<Order>> getOrdersByUserId(Long id){
    List<Order> order = null;
    order = orderRepo.findAll().stream().filter(o -> o.getUser().getId() == id).toList();
    if(order == null){
      return HttpResponse.badRequest(order);
    }
    return HttpResponse.ok(order);
  }

  // Create a new Order based on the JSON format seen from the GET calls.
  @Post
  public HttpResponse<Order> addOrder(@Body Order order){
    orderRepo.save(order);
    return HttpResponse.created(order);
  }

  // Update an existing Order
  @Put("/{id}")
  public HttpResponse<Order> updateOrder(@Body Order order, Long id){
    order.setId(id);
    orderRepo.update(order);
    return HttpResponse.ok(order);
  }

  // Delete an Order by Id
  @Delete("/{id}")
  public HttpResponse<Order> deleteOrder(Long id){
    Order order = orderRepo.findById(id).orElse(null);
    if(order == null){
      return HttpResponse.badRequest();
    }
    orderRepo.delete(order);
    return HttpResponse.ok(order);
  }
}
