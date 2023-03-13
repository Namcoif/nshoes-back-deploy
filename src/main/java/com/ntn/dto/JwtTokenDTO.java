package com.ntn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDTO {
    private String username;
    private String token;
    private String role;
    private String userId;
}
