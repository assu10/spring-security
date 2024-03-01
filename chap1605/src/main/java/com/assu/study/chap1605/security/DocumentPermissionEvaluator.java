package com.assu.study.chap1605.security;

import com.assu.study.chap1605.model.Document;
import com.assu.study.chap1605.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@RequiredArgsConstructor
@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {
  private final DocumentRepository documentRepository;

  @Override
  public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
    // 사용하지 않으므로 구현하지 않음
    return false;
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
    // 객체는 없지만 객체 ID 가 있으므로 ID 로 객체를 얻음
    String code = targetId.toString();
    Document document = documentRepository.findDocument(code);

    // 이 경우에 permission 객체는 역할 이름이므로 String 형식으로 변환
    String p = (String) permission;

    // 사용자에게 매개 변수로 받은 역할이 있는지 검증
    boolean manager = authentication.getAuthorities()
        .stream()
        .anyMatch(a -> a.getAuthority().equals(p));

    return manager || document.owner().equals(authentication.getName());
  }
}
