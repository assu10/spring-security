package com.assu.study.chap1102.authtication.proxy;

import com.assu.study.chap1102.authtication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component  // 이 형식의 인스턴스를 컨텍스트에 포함시킴
public class AuthenticationServerProxy {

  private final RestTemplate restTemplate;

  @Value("${auth.server.base.url}")
  private String baseUrl;

  /**
   * 사용자 이름/암호 인증을 수행
   */
  public void sendAuth(String username, String password) {
    String url = baseUrl + "/user/auth";

    User body = new User();
    body.setUsername(username);
    body.setPassword(password);

    HttpEntity request = new HttpEntity<>(body);

    restTemplate.postForEntity(url, request, Void.class);
  }

  /**
   * 사용자 이름/OTP 인증을 수행
   */
  public boolean sendOtp(String username, String code) {
    String url = baseUrl + "/otp/check";

    User body = new User();
    body.setUsername(username);
    body.setCode(code);

    HttpEntity request = new HttpEntity<>(body);

    ResponseEntity response = restTemplate.postForEntity(url, request, Void.class);

    return response.getStatusCode().equals(HttpStatus.OK);
  }
}
