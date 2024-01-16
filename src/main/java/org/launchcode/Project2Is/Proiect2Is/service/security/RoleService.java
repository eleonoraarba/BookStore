package org.launchcode.Project2Is.Proiect2Is.service.security;

import org.launchcode.Project2Is.Proiect2Is.model.Role;
import org.launchcode.Project2Is.Proiect2Is.repository.user.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role findByRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }

    public List<Role> getAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }
}