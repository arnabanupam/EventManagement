package com.shopdeck.eventmanagement.repository;

import com.shopdeck.eventmanagement.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {

}

