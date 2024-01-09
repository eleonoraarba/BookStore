package org.launchcode.Project2Is.Proiect2Is.service.security;

import org.launchcode.Project2Is.Proiect2Is.model.RoleUser;
import org.launchcode.Project2Is.Proiect2Is.model.User;
import org.launchcode.Project2Is.Proiect2Is.repository.security.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl implements RoleUserService{


    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    public void insertUserRole(User user, String role) {

        RoleUser roleUser = new RoleUser(user.getId(), role);
        roleUserRepository.save(roleUser);
    }

    @Override
    public String findRoleById(Long id) {
        RoleUser roleUser= roleUserRepository.findById(id);
        if(roleUser!=null){
            return roleUser.getRole();
        }
        else{
            return null;
        }
    }

    @Override
    public void updateUserRole(Long userId, String newRole) {
        RoleUser existingRoleUser = roleUserRepository.findById(userId);

        if (existingRoleUser != null && newRole!=null) {
            existingRoleUser.setRole(newRole);
            roleUserRepository.save(existingRoleUser);
        }
    }

    @Override
    public void deleteById(Long id) {
        roleUserRepository.deleteById(Math.toIntExact(id));
    }


}
