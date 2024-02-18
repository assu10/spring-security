package com.assu.study.chap1004.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class HelloController {
  @GetMapping("/")
  public String main() {
    return "main.html";
  }

  @PostMapping("/test")
  @ResponseBody
  //@CrossOrigin("http://localhost:8080")
  public String test() {
    log.info("TEST Method");
    return "hello";
  }
}
