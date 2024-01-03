package org.launchcode.Project2Is.Proiect2Is.service.authentification;

import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.springframework.stereotype.Component;

@Component
public interface AuthentificationService {
    String createAccount(User user);
    User logIn(String username, String password);
    User findUserByUsername(String username);
}
