package com.ntn.dto;

import com.ntn.entity.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private Integer id;
    private Role.RoleEnum role;
}
