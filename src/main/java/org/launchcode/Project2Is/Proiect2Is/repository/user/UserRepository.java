package org.launchcode.Project2Is.Proiect2Is.repository.user;

import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    List<User> findAll();
    User findById(Long userId);
    void deleteById(Long id);
    User findUserByUsername(String username);

}
