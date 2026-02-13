package com.org.event.book.repository;

import com.org.event.book.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategory(String category);
    List<Event> save(List<Event> category);

}
