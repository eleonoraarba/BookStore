package org.launchcode.demo.data;

import org.launchcode.demo.models.Event;
import org.launchcode.demo.models.EventCategory;
import org.springframework.data.repository.CrudRepository;

public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
