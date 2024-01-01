package com.safestreets.services;

import com.safestreets.model.Product;
import com.safestreets.model.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductService
 * Created By: coreydenning
 * Created On: 12/29/23
 **/
@Singleton
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product createNewProduct(String name, double price) {
    Product product = new Product().withName(name).withPrice(price);
    product = productRepository.save(product);
    if(product.getId() > 0){
      log.info("Product creation success! Id: " + product.getId());
    }
    return product;
  }

}
