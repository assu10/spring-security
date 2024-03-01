package com.assu.study.chap1703.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Event {

  @Id
  private Integer id;

  private String name;

  private String owner;
}
