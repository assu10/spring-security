package com.assu.study.chap1604.repository;

import com.assu.study.chap1604.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {
  private final Map<String, Document> documents =
      Map.of("assuDoc", new Document("assu"),
          "coolDoc", new Document("assu"),
          "silbyDoc", new Document("silby")
      );

  public Document findDocument(String code) {
    return documents.get(code);
  }
}
