package org.launchcode.Project2Is.Proiect2Is.service.authentification;

import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class AuthentificationServiceImpl implements AuthentificationService{

    @Autowired
    private UserRepository userRepository;

    public AuthentificationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Override
    public User logIn(String username, String password) {
        User user= findUserByUsername(username);

        if(user != null && hashPassword(password).equals(user.getPassword())){

            return user;
        }
        else{
            return null;
        }

    }

    @Override
    public String createAccount(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return "All fields are required";
        } else if (!(user.getUsername().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))) {
            return "Invalid email address";
        } else if (!user.getPassword().matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$")) {
            return "Password must contain at least one uppercase letter, one digit, and one special character";
        } else if (findUserByUsername(user.getUsername())!=null) {
            return "An account with this email address already exists";
        } else {
            user.setPassword(hashPassword(user.getPassword()));
            userRepository.save(user);
            return "Account Created";
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
