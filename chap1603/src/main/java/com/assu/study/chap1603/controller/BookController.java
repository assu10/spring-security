package com.assu.study.chap1603.controller;

import com.assu.study.chap1603.model.Employee;
import com.assu.study.chap1603.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookController {
  private final BookService bookService;

  @GetMapping("/book/details/{name}")
  public Employee getBook(@PathVariable String name) {
    return bookService.getBooks(name);
  }
}
