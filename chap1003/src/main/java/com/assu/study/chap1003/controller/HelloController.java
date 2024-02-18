package com.assu.study.chap1003.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "get hello";
  }

  @PostMapping("/hello")
  public String hello2(HttpServletRequest request) {
    return "post hello";
  }

  @GetMapping("/world")
  public String world() {
    return "get world";
  }

  @PostMapping("/world")
  public String world2() {
    return "post world";
  }
}
