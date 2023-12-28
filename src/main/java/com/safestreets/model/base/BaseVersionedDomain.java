package com.safestreets.model.base;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * BaseVersionedDomain
 * Created By: dylanthompson
 * Created On: 3/2/23
 **/
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseVersionedDomain {
  @Id
  @GeneratedValue
  private Long id;

  @Version
  @NonNull
  private Integer version = 0;
}
