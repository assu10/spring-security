package com.assu.study.chap1102.authtication.filter;

import com.assu.study.chap1102.authtication.OtpAuthentication;
import com.assu.study.chap1102.authtication.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

  // 인증 관리자, 인증 책임을 위임
  private final AuthenticationManager authenticationManager;

  @Value("${jwt.signing.key}")
  private String SIGNING_KEY;

  /**
   * 요청이 필터 체인에서 이 필터에 도달할 때 호출됨
   * <p>
   * OTP 를 얻기 위해 사용자 이름/암호를 보내는 인증 논리 구현
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // HTTP 요청 헤더를 통해 자격 인증 정보가 넘어옴
    String username = request.getHeader("username");
    String password = request.getHeader("password");
    String code = request.getHeader("code");

    // 헤더에 OTP 가 없으면 사용자 이름/암호 인증으로 가정
    if (code == null) {
      Authentication authentication = new UsernamePasswordAuthentication(username, password);

      // 따라서 OTP 가 없으면 UsernamePasswordAuthentication 인스턴스를 만든 후 책임을 AuthenticationManager 에게 전달하여 첫 번째 인증단계 호출
      authenticationManager.authenticate(authentication);
    } else {
      // 헤더에 OTP 가 있으면 사용자 이름/OTP 인증 단계라고 가정
      // OtpAuthentication 형식의 인스턴스를 만든 후 책임을 AuthenticationManager 에게 전달하여 두 번째 인증단계 호출
      Authentication authentication = new OtpAuthentication(username, code);
      authenticationManager.authenticate(authentication);

      // OtpAuthenticationProvider 에 OTP 가 틀리면 인증이 실패하면서 예외가 발생하므로
      // OTP 가 유효할 때만 JWT 토큰이 생성되고, HTTP 응답 헤더에 포함됨

      // 대칭키로 서명 생성
      SecretKey key = Keys.hmacShaKeyFor(SIGNING_KEY.getBytes(StandardCharsets.UTF_8));

      // TODO: deprecated
      // JWT 생성 후 인증된 사용자의 이름을 클레임 중 하나로 저장함
      // 토큰을 서명하는데 키를 이용함
      String jwt = Jwts.builder()
          .setClaims(Map.of("username", username))  // 본문에 값 추가
          .signWith(key)  // 비대칭키로 토큰에 서명 첨부
          .compact();

      // 토큰을 HTTP 응답의 권한 부여 헤더에 추가
      response.setHeader("Authorization", jwt);
    }

    // AuthenticationManager 는 해당하는 AuthenticationProvider 를 찾음
    // UsernamePasswordAuthenticationProvider.supports() 가 UsernamePasswordAuthentication 형식을 받는다고
    // 선언하였으므로 UsernamePasswordAuthenticationProvider 로 인증 책임을 위임함
  }

  /**
   * /login 경로가 아니면 이 필터를 실행하지 않음
   */
  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return !request.getServletPath().equals("/login");
  }
}
