package com.assu.study.chap1603.service;

import com.assu.study.chap1603.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
  private final Map<String, Employee> records =
      Map.of("assu",
          new Employee("assu1",
              List.of("assubook1", "assubook2"),
              List.of("manager", "reader")),
          "silby",
          new Employee("silby",
              List.of("silbybook1", "silbybookk2"),
              List.of("resercher")));

  // 사후 권한 부여를 위한 식
  // returnObject 메서드가 반환한 값을 참조하며, 메서드가 실행된 후 제공되는 메서드 반환값을 이용
  @PostAuthorize("returnObject.roles.contains('reader')")
  public Employee getBooks(String name) {
    return records.get(name);
  }
}
