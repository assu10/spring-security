package com.assu.study.chap0901.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RequestValidationFilter implements Filter {
  /**
   * 필터의 논리 작성
   * <p>
   * 헤더에 Request-id 가 있는지 확인 후 있으면 doFilter() 를 호출하여 체인의 다음 필터로 요청 전달
   * 없으면 다음 필터로 요청을 전달하지 않고 HTTP 400 반환
   */
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

    String requestId = httpRequest.getHeader("Request-id");

    if (requestId == null || requestId.isBlank()) {
      httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 response
      return; // 요청이 다음 필터로 전달되지 않음
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
