package com.assu.study.chap1001.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

@Slf4j
public class CsrfTokenLogger implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    // _csrf 요청 특성에서 토큰값을 얻어 콘솔에 출력
    Object object = servletRequest.getAttribute("_csrf");
    CsrfToken token = (CsrfToken) object;

    log.info("CSRF Token: " + token.getToken());

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
