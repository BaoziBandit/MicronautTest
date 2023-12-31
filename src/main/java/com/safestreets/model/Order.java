package com.safestreets.model;

import java.util.List;

import com.safestreets.model.base.BaseVersionedDomain;

import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
@MappedEntity
public class Order extends BaseVersionedDomain {
  User user;
  @Relation(value = Relation.Kind.MANY_TO_MANY)
  @Join("product")
  List<Product> products;
}
