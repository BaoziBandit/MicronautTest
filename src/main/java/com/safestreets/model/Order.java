package com.safestreets.model;

import java.util.ArrayList;
import java.util.List;

import com.safestreets.model.base.BaseVersionedDomain;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.Relation.Cascade;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
@MappedEntity
public class Order extends BaseVersionedDomain {
  User user;

  // TODO: Need to figure out why List<Product> isn't being returned with query. 
  // The Join annotation doesn't make any difference.
  @Relation(value = Relation.Kind.MANY_TO_MANY, cascade = Cascade.ALL)
  List<Product> products = new ArrayList<>();

  public Order withUser(User user){
    this.user = user;
    return this;
  }

  public Order withProduct(Product product){
    this.products.add(product);    
    return this;
  }

  public Order withProducts(List<Product> products){
    this.products.addAll(products);
    return this;
  }
}
