package org.launchcode.Project2Is.Proiect2Is.model.builder;

import org.launchcode.Project2Is.Proiect2Is.model.User;

public class UserBuilder {
    private User user;

    public UserBuilder(){
        user = new User();
    }

    public UserBuilder setId(Long id){
        user.setId(id);
        return this;
    }

    public UserBuilder setUsername(String username){
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password){
        user.setPassword(password);
        return this;
    }

    public UserBuilder setAdmin (boolean admin){
        user.setAdmin(admin);
        return this;
    }
    public User build(){
        return user;
    }

}
