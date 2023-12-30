package com.safestreets.controllers;

import java.util.List;

import com.safestreets.model.Product;
import com.safestreets.model.repository.ProductRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
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
  public HttpResponse<Product> getProduct(Long id){
    Product product = productRepo.findById(id).orElse(null);
    return HttpResponse.ok(product);
  }

  // Add a new Product based on Get JSON output formatted data
  @Post
  public HttpResponse<Product> addProduct(@Body Product product){
    productRepo.save(product);
    return HttpResponse.created(product);
  }

  // Update specified Product by ID with Get JSON output formatted data.
  @Put("/{id}")
  public HttpResponse<Product> updateProduct(@Body Product product, Long id){
    product.setId(id);
    productRepo.update(product);
    return HttpResponse.ok(product);
  }

  // Delete specified Product by Id
  @Delete("/{id}")
  public HttpResponse<Product> deleteProduct(Long id){
    Product product = productRepo.findById(id).orElse(null);
    if(product == null){
      return HttpResponse.badRequest();
    }
    productRepo.delete(product);
    return HttpResponse.ok(product);
  }
}
