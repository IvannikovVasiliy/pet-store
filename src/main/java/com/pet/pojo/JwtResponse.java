package com.pet.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    public JwtResponse(String token, String username, List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
}
