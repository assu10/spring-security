package com.assu.study.chap1704.controller;

import com.assu.study.chap1704.entity.Event;
import com.assu.study.chap1704.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class EventController {
  private final EventRepository eventRepository;

  @GetMapping("/events/{text}")
  public List<Event> findEventsContaining(@PathVariable String text) {
    return eventRepository.findByNameContaining(text);
  }
}
