package com.ntn.service;

import com.ntn.entity.Role;

public interface IRoleService {
    Role getRoleById(Integer id);

    Role getRoleByName(String role);
}
