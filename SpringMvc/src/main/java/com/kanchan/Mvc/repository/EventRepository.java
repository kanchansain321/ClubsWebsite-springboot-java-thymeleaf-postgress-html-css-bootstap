package com.kanchan.Mvc.repository;

import com.kanchan.Mvc.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
