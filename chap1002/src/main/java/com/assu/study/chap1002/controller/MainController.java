package com.assu.study.chap1002.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MainController {
  @GetMapping("/main")
  public String main() {
    return "main.html";
  }

  @PostMapping("/product/add")
  public String add(@RequestParam String name) {
    log.info("Adding: " + name);
    return "main.html";
  }
}
