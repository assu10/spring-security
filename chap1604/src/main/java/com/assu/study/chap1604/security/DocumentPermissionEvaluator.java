package com.assu.study.chap1604.security;

import com.assu.study.chap1604.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 권한 부여 규칙의 구현
 */
@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {
  @Override
  public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
    // targetDomainObject 객체를 Document 형식으로 변환
    Document document = (Document) targetDomainObject;
    // 이 경우에 permission 객체는 역할 이름이므로 String 형식으로 변환
    String p = (String) permission;

    // 사용자에게 매개 변수로 받은 역할이 있는지 검증
    boolean manager =
        authentication.getAuthorities()
            .stream()
            .anyMatch(a -> a.getAuthority().equals(p));

    return manager || document.owner().equals(authentication.getName());
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
    // 사용하지 않으므로 구혀하지 않ㅇㅁ
    return false;
  }
}
