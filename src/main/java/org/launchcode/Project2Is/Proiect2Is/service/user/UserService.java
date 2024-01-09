package org.launchcode.Project2Is.Proiect2Is.service.user;

import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    List<User> findAll();
    void insert(User user);
    User findById(Long userId);
    void deleteById(Long id);
    User findUserByUsername(String username);
    void updateUser(Long id, String newUsername, String newPassword);

}
