package com.assu.study.chap1102.authtication.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 인증 서버가 노출하는 REST API 를 호출할 때 사용할 모델 클래스
 */
@Getter
@Setter
public class User {
  private String username;
  private String password;
  private String code;
}
