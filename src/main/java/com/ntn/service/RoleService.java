package com.ntn.service;

import com.ntn.entity.Role;
import com.ntn.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.getById(id);
    }

    @Override
    public Role getRoleByName(String role) {
        return roleRepository.findRoleByName(role);
    }
}
