package com.assu.study.chap1102.authtication.filter;

import com.assu.study.chap1102.authtication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * JWT 기반 인증 관련 필터
 * <p>
 * 요청의 권한 부여 HTTP 헤더에 JWT 가 있다고 가정하고, 서명을 확인하여 JWT 검증 후
 * 인증된 Authentication 객체를 만들어 SecurityContext 에 추가함
 */
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Value("${jwt.signing.key}")
  private String SIGNING_KEY;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwt = request.getHeader("Authorization");

    SecretKey key = Keys.hmacShaKeyFor(SIGNING_KEY.getBytes(StandardCharsets.UTF_8));

    // TODO: deprecated
    // 토큰을 구문 분석하여 클레임을 얻고, 서명을 검증함
    // 서명이 유효하지 않으면 예외가 발생함
    Claims claims = Jwts.parser()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(jwt)
        .getBody();

    String username = String.valueOf(claims.get("username"));

    // SecurityContext 에 추가할 Authentication 인스턴스 생성
    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");

    // 인증이 되었으므로 매개변수가 3개인 생성자로 Authentication 인스턴스 생성
    UsernamePasswordAuthentication auth = new UsernamePasswordAuthentication(username, null, List.of(grantedAuthority));

    // SecurityContext 에 Authentication 객체 추가
    SecurityContextHolder.getContext().setAuthentication(auth);

    // 필터 체인의 다음 필터 호출
    filterChain.doFilter(request, response);
  }

  /**
   * /login 경로이면 이 필터를 실행하지 않음
   * 즉, /login 외의 다른 모든 경로에 대한 요청 처리
   */
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return request.getServletPath().equals("/login");
  }
}
