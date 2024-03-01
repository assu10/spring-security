package com.assu.study.chap1703.repository;

import com.assu.study.chap1703.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

  @PostFilter("filterObject.owner == authentication.principal.username")
  List<Event> findByNameContaining(String text);
}
