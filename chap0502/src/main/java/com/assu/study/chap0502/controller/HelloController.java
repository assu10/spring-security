package com.assu.study.chap0502.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
//  @GetMapping("hello")
//  public void hello(Authentication authentication) {
//    System.out.println(authentication.getName());
//  }
//
//  @GetMapping("hello2")
//  @Async
//  public void hello2() {
//    SecurityContext securityContext = SecurityContextHolder.getContext();
//    String name = securityContext.getAuthentication().getName();  // NPE 발생
//    System.out.println(name);
//  }

  // `DelegatingSecurityContextCallable` 로 현재 컨텍스트를 새로운 스레드에 복사
//  @GetMapping("delegatingTest")
//  public void delegatingTest() throws ExecutionException, InterruptedException {
//    Callable<String> task = () -> {
//      SecurityContext securityContext = SecurityContextHolder.getContext();
//      return securityContext.getAuthentication().getName();
//    };
//
//    ExecutorService executorService = Executors.newCachedThreadPool();
//
//    try {
//      // 현재의 컨텍스트를 새로운 스레드에 제공
//      DelegatingSecurityContextCallable contextTask = new DelegatingSecurityContextCallable<>(task);
//      // 실행할 작업을 ExecutorService 에 제출하고 실행
//      System.out.println(executorService.submit(contextTask).get());
//    } finally {
//      executorService.shutdown();
//    }
//  }

  // `DelegatingSecurityContextExecutorService` 가 `ExecutorService` 를 장식하여
  // 작업을 제출할 때 `SecurityContext` 세부 정보를 전파
  @GetMapping("delegatingTest2")
  public void delegatingTest2() throws ExecutionException, InterruptedException {
    Callable<String> task = () -> {
      SecurityContext securityContext = SecurityContextHolder.getContext();
      return securityContext.getAuthentication().getName();
    };

    ExecutorService executorService = Executors.newCachedThreadPool();
    // 새로운 스레드로 컨텍스트 전파 시 작업에서 하지 않고 스레드 풀에서 전파 관리
    executorService = new DelegatingSecurityContextExecutorService(executorService);

    try {
      System.out.println(executorService.submit(task).get());
    } finally {
      executorService.shutdown();
    }
  }
}
