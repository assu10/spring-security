package com.assu.study.chap0601.entity;

import com.assu.study.chap0601.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Double price;

  @Enumerated(EnumType.STRING)
  private Currency currency;
}
