package com.safestreets.model.repository;

import java.util.List;

import com.safestreets.model.Order;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
@Join("user")
@Join(value = "products", type = Join.Type.LEFT_FETCH)
public interface OrderRepository extends CrudRepository<Order, Long> {
  public List<Order> findAll();
  public List<Order> findAllByUserId(Long id);
  public Order updateById(Long id, Order order);
  public void deleteById(Long id);
}
