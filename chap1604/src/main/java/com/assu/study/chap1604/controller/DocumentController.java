package com.assu.study.chap1604.controller;

import com.assu.study.chap1604.model.Document;
import com.assu.study.chap1604.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocumentController {
  private final DocumentService documentService;

  @GetMapping("/documents/{code}")
  public Document getDocuments(@PathVariable String code) {
    return documentService.getDocument(code);
  }
}
