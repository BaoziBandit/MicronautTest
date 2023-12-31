package com.safestreets.controllers;

import java.util.List;

import com.safestreets.model.User;
import com.safestreets.model.repository.UserRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
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
  public HttpResponse<User> getUser(@PathVariable Long id){;
    return HttpResponse.ok(userRepo.findById(id).orElse(null));
  }

  // Add new User based on Get output formatted data
  @Post
  public HttpResponse<User> addUser(@Body User user){
    return HttpResponse.created(userRepo.save(user));
  }

  // Updated specified User by Id based on Get output formatted data
  @Put("/{id}")
  public HttpResponse<User> updateUser(@Body User user, @PathVariable Long id){
    return HttpResponse.ok(userRepo.updateById(id, user));
  }

  // Delete specified User by Id
  @Delete("/{id}")
  public HttpResponse<User> deleteUser(@PathVariable Long id){
    userRepo.deleteById(id);
    return HttpResponse.ok();
  }
}
