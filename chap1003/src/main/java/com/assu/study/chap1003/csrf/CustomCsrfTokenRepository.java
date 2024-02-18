package com.assu.study.chap1003.csrf;

import com.assu.study.chap1003.entity.Token;
import com.assu.study.chap1003.repository.JpaTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import java.util.Optional;
import java.util.UUID;

/**
 * CsrfTokenRepository 구현
 */
@Slf4j
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

  @Autowired
  private JpaTokenRepository jpaTokenRepository;

  /**
   * CSRF 토큰 생성
   */
  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    String uuid = UUID.randomUUID().toString();
    log.info("generateToken: " + uuid);
    return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
  }

  /**
   * CSRF 토큰을 DB 에 저장
   */
  @Override
  public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
    String identifier = request.getHeader("X-IDENTIFIER");
    Optional<Token> existingToken = jpaTokenRepository.findByIdentifier(identifier);

    log.info("saveToken existingToken: " + existingToken);

    // 해당 클라이언트 식별자로 토큰이 이미 저장되어 있으면 새로 받은 토큰으로 저장함
    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      token.setToken(csrfToken.getToken());
    } else {
      // 해당 클라이언트 식별자로 토큰이 없을 경우 신규로 저장
      Token token = new Token();
      token.setToken(csrfToken.getToken());
      token.setIdentifier(identifier);
      jpaTokenRepository.save(token);
    }
  }

  /**
   * CSRF 토큰 조회
   */
  @Override
  public CsrfToken loadToken(HttpServletRequest request) {
    String identifier = request.getHeader("X-IDENTIFIER");
    Optional<Token> existingToken = jpaTokenRepository.findByIdentifier(identifier);

    // 해당 클라이언트 식별자로 저장된 토큰이 있을 경우 토큰을 조회하여 DefaultCsrfToken 형태로 토큰 리턴
    if (existingToken.isPresent()) {
      Token token = existingToken.get();
      return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
    }

    return null;
  }
}
