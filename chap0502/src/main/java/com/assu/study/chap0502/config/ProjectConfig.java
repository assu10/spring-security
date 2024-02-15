package com.assu.study.chap0502.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync  // @Async 애너테이션 기능 활성화
public class ProjectConfig {
  @Bean
  public InitializingBean initializingBean() {
    return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
  }
}
