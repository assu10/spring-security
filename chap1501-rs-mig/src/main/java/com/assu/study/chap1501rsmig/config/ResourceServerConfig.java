package com.assu.study.chap1501rsmig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 대칭키를 이용한 JWT 인증 구성
 */
@Configuration
public class ResourceServerConfig {

  @Value("${jwt.key}")
  private String JWT_KEY;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authz -> authz.anyRequest().authenticated()) // 모든 요청은 인증이 있어야 함
        .oauth2ResourceServer(c -> c.jwt(jwt -> {
          jwt.decoder(jwtDecoder());
        }));

    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    byte[] key = JWT_KEY.getBytes();
    SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");

    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();

    return jwtDecoder;
  }
}

