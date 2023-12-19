package org.launchcode.demo.data;

import org.launchcode.demo.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRespository extends CrudRepository<Event, Integer> {

}
