package com.assu.study.chap1102.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 인증 서버가 노출하는 REST API 를 호출하는데 이용할 RestTemplate 형식의 빈 선언
 */
@Configuration
public class ProjectConfig {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
