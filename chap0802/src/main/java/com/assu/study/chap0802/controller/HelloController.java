package com.assu.study.chap0802.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("/aa")
  public String hello() {
    return "get aa";
  }

  @PostMapping("/aa")
  public String bye() {
    return "post aa";
  }

  @GetMapping("/aa/bb")
  public String see() {
    return "get aa/bb";
  }

  @GetMapping("/aa/bb/cc")
  public String see2() {
    return "get aa/bb/cc";
  }

  @GetMapping("/aa/bb/dd")
  public String see3() {
    return "get aa/bb/dd";
  }

  @GetMapping("/aa/bb/cc/dd")
  public String see4() {
    return "get aa/bb/cc/dd";
  }

  @GetMapping("product/{code}")
  public String see5(@PathVariable String code) {
    return code;
  }
}
