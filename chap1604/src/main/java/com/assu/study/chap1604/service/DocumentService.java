package com.assu.study.chap1604.service;

import com.assu.study.chap1604.model.Document;
import com.assu.study.chap1604.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentService {
  private final DocumentRepository documentRepository;

  // hasPermission() 메서드는 추가로 구현할 외부 권한 부여식을 참조할 수 있게 함
  // hasPermission() 메서드의 매개 변수는 메서드에서 반환된 값을 나타내는 returnObject 와
  // 접근을 허용하는 역할의 이름인 ROLE_manager 임
  @PostAuthorize("hasPermission(returnObject, 'ROLE_manager')")
  public Document getDocument(String code) {
    return documentRepository.findDocument(code);
  }
}
