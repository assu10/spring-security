package com.assu.study.chap1701.service;

import com.assu.study.chap1701.model.Event;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

  // Event 의 owner 특성이 로그인한 사용자의 이름과 같은 값만 허용
  @PreFilter("filterObject.owner == authentication.name")
  public List<Event> getEvents(List<Event> events) {
    return events;
  }
}
