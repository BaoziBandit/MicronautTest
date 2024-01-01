package com.safestreets.model;

import com.safestreets.model.base.BaseVersionedDomain;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;

/**
 * User
 * Created By: dylanthompson
 * Created On: 12/28/23
 **/
@Getter
@Setter
@Serdeable
@MappedEntity
public class User extends BaseVersionedDomain {
  String name;
  String email;
  Role role;

  public User withName(String name){
    this.name = name;
    return this;
  }
  public User withEmail(String email){
    this.email = email;
    return this;
  }
  public User withRole(String role){
    this.role = Role.valueOf(role);
    return this;
  }
  enum Role{
    ADMIN, CUSTOMER
  }
}
