package com.assu.study.chap1201.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {
  @GetMapping("/")
  public String main(OAuth2AuthenticationToken token) {
    log.info("token: " + token);
    log.info("principal: " + token.getPrincipal());


    SecurityContext securityContext = SecurityContextHolder.getContext();
    log.info("securityContext getAuthentication: " + securityContext.getAuthentication());
    log.info("securityContext principal: " + securityContext.getAuthentication().getPrincipal());

    return "main.html";
  }
}
