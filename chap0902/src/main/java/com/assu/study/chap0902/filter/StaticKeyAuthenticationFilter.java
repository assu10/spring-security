package com.assu.study.chap0902.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component  // 속성 파일에서 값을 주입할 수 있도록 스프링 컨텍스트에 클래스 인스턴스 추가
public class StaticKeyAuthenticationFilter extends OncePerRequestFilter {

  @Value("${authorization.key}")
  private String AUTHORIZATION_KEY;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String staticKey = request.getHeader("Authorization");

    if (AUTHORIZATION_KEY.equals(staticKey)) {
      filterChain.doFilter(request, response);
    } else {
      // 다음 필터로 요청을 전달하지 않고 401 응답
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
    }
  }
}
