package com.assu.study.chap1702.controller;

import com.assu.study.chap1702.model.Event;
import com.assu.study.chap1702.service.EventService;
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
    return eventService.getEvents();
  }
}
