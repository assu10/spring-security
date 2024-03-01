package com.assu.study.chap1605.service;

import com.assu.study.chap1605.model.Document;
import com.assu.study.chap1605.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentService {
  private final DocumentRepository documentRepository;

  @PreAuthorize("hasPermission(#code, 'document', 'ROLE_manager')")
  public Document getDocument(String code) {
    return documentRepository.findDocument(code);
  }
}
