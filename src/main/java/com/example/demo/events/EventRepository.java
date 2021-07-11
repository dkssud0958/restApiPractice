package com.example.demo.events;

import org.springframework.data.jpa.repository.*;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
