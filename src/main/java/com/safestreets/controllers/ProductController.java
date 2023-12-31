package com.safestreets.controllers;

import java.util.List;

import com.safestreets.model.Product;
import com.safestreets.model.repository.ProductRepository;

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
@Controller("/product")
public class ProductController {

  @Inject ProductRepository productRepo;

  // Get list of all Products
  @Get
  public List<Product> getProducts(){
    return productRepo.findAll();
  }
  
  // Get specified Product by Id
  @Get("/{id}")
  public HttpResponse<Product> getProduct(@PathVariable Long id){
    return HttpResponse.ok(productRepo.findById(id).orElse(null));
  }

  // Add a new Product based on Get JSON output formatted data
  @Post
  public HttpResponse<Product> addProduct(@Body Product product){
    return HttpResponse.created(productRepo.save(product));
  }

  // Update specified Product by ID with Get JSON output formatted data.
  @Put("/{id}")
  public HttpResponse<Product> updateProduct(@Body Product product, @PathVariable Long id){
    return HttpResponse.ok(productRepo.updateProductById(id, product));
  }

  // Delete specified Product by Id
  @Delete("/{id}")
  public HttpResponse<Product> deleteProduct(@PathVariable Long id){
    productRepo.deleteById(id);
    return HttpResponse.ok();
  }
}
