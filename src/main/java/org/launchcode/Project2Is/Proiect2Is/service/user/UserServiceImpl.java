package org.launchcode.Project2Is.Proiect2Is.service.user;

import org.launchcode.Project2Is.Proiect2Is.model.Role;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void insert(User user) {

        user.setPassword(hashPassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteById(Long id) {
       userRepository.deleteById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    @Override
    public void updateUser(Long id, String newUsername, String newPassword) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        User existingUser = userRepository.findById(id);

        if (existingUser != null) {

            if (newUsername != null && !newUsername.isEmpty()) {
                existingUser.setUsername(newUsername);
            }

            if (newPassword != null && !newPassword.isEmpty()) {
                existingUser.setPassword(newPassword);
            }

            this.insert(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }


    private String hashPassword(String password) {
        try {
            // Sercured Hash Algorithm - 256
            // 1 byte = 8 biți
            // 1 byte = 1 char
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
