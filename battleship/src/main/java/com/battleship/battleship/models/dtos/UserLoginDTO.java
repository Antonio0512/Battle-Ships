package com.battleship.battleship.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    @Size(min = 3)
    @NotBlank
    private String password;

    public UserLoginDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
