package com.safestreets.model.repository;

import com.safestreets.model.Order;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.H2)
public interface OrderRepository extends CrudRepository<Order, Long> {
}
