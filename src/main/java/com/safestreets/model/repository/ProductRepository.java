package com.safestreets.model.repository;

import com.safestreets.model.Product;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
public interface ProductRepository extends CrudRepository<Product, Long> {
  public Product updateProductById(Long id, Product product);
  public void deleteById(Long id);
}
