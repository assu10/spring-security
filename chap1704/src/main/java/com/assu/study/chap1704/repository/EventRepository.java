package com.assu.study.chap1704.repository;

import com.assu.study.chap1704.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

  @Query("SELECT e FROM Event e WHERE e.name LIKE %:text% AND e.owner=?#{authentication.principal.username}")
  List<Event> findByNameContaining(String text);
}
