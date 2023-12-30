package com.safestreets.controllers;

import java.util.List;

import com.safestreets.model.User;
import com.safestreets.model.repository.UserRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject;

// For simplicity, pagination is not being used.
@Controller("/user")
public class UserController {

  @Inject UserRepository userRepo;

  // Get list of all Users
  @Get
  public List<User> getUsers(){
    return userRepo.findAll();
  }
  
  // Get specified User by Id
  @Get("/{id}")
  public HttpResponse<User> getUser(Long id){
    User user = userRepo.findById(id).orElse(null);
    return HttpResponse.ok(user);
  }

  // Add new User based on Get output formatted data
  @Post
  public HttpResponse<User> addUser(@Body User user){
    userRepo.save(user);
    return HttpResponse.created(user);
  }

  // Updated specified User by Id based on Get output formatted data
  @Put("/{id}")
  public HttpResponse<User> updateUser(@Body User user, Long id){
    user.setId(id);
    userRepo.update(user);
    return HttpResponse.ok(user);
  }

  // Delete specified User by Id
  @Delete("/{id}")
  public HttpResponse<User> deleteUser(Long id){
    User user = userRepo.findById(id).orElse(null);
    if(user == null){
      return HttpResponse.badRequest();
    }
    userRepo.delete(user);
    return HttpResponse.ok(user);
  }
}
