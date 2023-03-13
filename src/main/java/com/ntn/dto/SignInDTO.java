package com.ntn.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignInDTO {

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be a blank")
    private String username;

    @Length(min = 6, max = 50, message = "Password must has between 6 and 50 character")
    private String password;
}
