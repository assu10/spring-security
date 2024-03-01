package com.assu.study.chap1603.model;

import java.util.List;

/**
 * 이름, 책 목록, 역할 목록이 들어있는 객체
 */
public record Employee(String name, List<String> books, List<String> roles) {
}
