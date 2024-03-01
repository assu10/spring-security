package com.assu.study.chap1701.controller;

import com.assu.study.chap1701.model.Event;
import com.assu.study.chap1701.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventController {
  private final EventService eventService;

  @GetMapping("/events")
  public List<Event> getEvents() {
//    List<Event> events = new ArrayList<>();
//
//    events.add(new Event("assuEvent1", "assu"));
//    events.add(new Event("assuEvent2", "assu"));
//    events.add(new Event("silbyEvent1", "silby"));

    // 변경 불가능한 인스턴스
    List<Event> events = List.of(
        new Event("assuEvent1", "assu"),
        new Event("assuEvent2", "assu"),
        new Event("silbyEvent1", "silby")
    );

    return eventService.getEvents(events);
  }
}
