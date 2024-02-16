package com.assu.study.chap0601.controller;

import com.assu.study.chap0601.repository.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
  private final ProductService productService;

  @GetMapping("main")
  public String main(Authentication authentication, Model model) {
    // UsernamePasswordAuthenticationToken [Principal=assu, Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[READ, WRITE]]
    System.out.println("controller authentication: " + authentication);

    model.addAttribute("username", authentication.getName());
    model.addAttribute("products", productService.findAll());
    return "main.html";
  }
}
