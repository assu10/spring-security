package com.assu.study.chap0601.repository;

import com.assu.study.chap0601.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }
}
