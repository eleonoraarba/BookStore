package org.launchcode.demo.data;

import org.launchcode.demo.models.EventCategory;
import org.launchcode.demo.models.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Integer> {
}
