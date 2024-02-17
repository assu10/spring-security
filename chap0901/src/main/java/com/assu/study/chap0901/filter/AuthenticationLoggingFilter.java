package com.assu.study.chap0901.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

// request 당 한번만 실행하기 위해 Filter 가 아닌 OncePerRequestFilter 구현
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

  private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    HttpServletRequest httpRequest = request;

    String requestId = httpRequest.getHeader("Request-id");

    logger.info("logging request-id: " + requestId);

    // 요청을 필터 체인의 다음 필터에 전달
    filterChain.doFilter(request, response);
  }
}
