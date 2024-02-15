package com.assu.study.chap0504.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {
  // 응답이 실패했을 때 응답에 헤더를 추가하고, HTTP 상태를 401 로 변경하는 예시
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.addHeader("message", "ASSU, Welcome");
    response.sendError(HttpStatus.UNAUTHORIZED.value());
  }
}
