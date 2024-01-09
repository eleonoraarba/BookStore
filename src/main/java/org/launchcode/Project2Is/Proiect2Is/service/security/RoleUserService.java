package org.launchcode.Project2Is.Proiect2Is.service.security;

import org.launchcode.Project2Is.Proiect2Is.model.User;

public interface RoleUserService {


    void insertUserRole(User user, String role);

    String findRoleById(Long id);

    void updateUserRole(Long userId, String newRole);
    void deleteById(Long id);
}
