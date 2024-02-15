package com.assu.study.chap0505.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  // 인증 성공 시 부여된 권한에 따라 다른 리디렉션 수행
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    response.sendRedirect("/home");
//    var authorities = authentication.getAuthorities();
//
//    var auth = authorities.stream()
//        .filter(a -> a.getAuthority().equals("read"))
//        .findFirst();
//
//    System.out.println("-----" + auth);
//    if (auth.isPresent()) {
//      response.sendRedirect("/home");
//    } else {
//      response.sendRedirect("/error");
//    }
  }
}
