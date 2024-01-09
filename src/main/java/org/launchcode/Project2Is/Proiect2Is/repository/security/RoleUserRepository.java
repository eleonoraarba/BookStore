package org.launchcode.Project2Is.Proiect2Is.repository.security;

import org.launchcode.Project2Is.Proiect2Is.model.RoleUser;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.service.security.RoleUserServiceImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepository extends CrudRepository<RoleUser, Integer> {
    RoleUser findById(Long id);


}
