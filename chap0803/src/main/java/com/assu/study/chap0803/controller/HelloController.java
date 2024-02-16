package com.assu.study.chap0803.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("user/{country}/{lang}")
  public String test(@PathVariable String country, @PathVariable String lang) {
    return "Country: " + country + ", lang: " + lang;
  }
}
