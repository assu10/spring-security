package com.assu.study.chap1602.controller;

import com.assu.study.chap1602.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HelloController {
  private final HelloService helloService;

  @GetMapping("/secret/names/{name}")
  public List<String> hello(@PathVariable String name) {
    return helloService.getSecretNames(name);
  }
}
