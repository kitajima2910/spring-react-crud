package com.javadocfast.crud.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private int jwtExpirationMs;
    private long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, int jwtExpirationMs, long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.jwtExpirationMs = jwtExpirationMs;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
