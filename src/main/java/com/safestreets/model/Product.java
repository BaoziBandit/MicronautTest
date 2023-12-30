package com.safestreets.model;

import com.safestreets.model.base.BaseVersionedDomain;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Serdeable
@MappedEntity
public class Product extends BaseVersionedDomain{
  String name;
  double price;

  public Product withName(String name){
    this.name = name;
    return this;
  }
  public Product withPrice(double price){
    this.price = price;
    return this;
  }
}
