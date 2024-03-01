package com.assu.study.chap1702.service;

import com.assu.study.chap1702.model.Event;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
  // Event 의 owner 특성이 로그인한 사용자의 이름과 같은 값만 허용
  @PostFilter("filterObject.owner == authentication.principal.username")
  public List<Event> getEvents() {
    List<Event> events = new ArrayList<>();

    events.add(new Event("assuEvent1", "assu"));
    events.add(new Event("assuEvent2", "assu"));
    events.add(new Event("silbyEvent1", "silby"));
    return events;
  }
}
