package com.assignment3.user.dto;

import lombok.Getter;

@Getter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;
}
