package com.devsuperior.Desafiotdd.repositories;

import com.devsuperior.Desafiotdd.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
