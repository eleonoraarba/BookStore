package org.launchcode.Project2Is.Proiect2Is.repository.user;

import org.launchcode.Project2Is.Proiect2Is.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
