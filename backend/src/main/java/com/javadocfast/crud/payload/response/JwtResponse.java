package com.javadocfast.crud.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
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
    private String fullName;
    private Date birthday; // 1995-10-29
    private int gender; // 0: Nam, 1: Nữ, 3: Khác
    private String address;
    private String postcode;
    private String phone;
    private String linkImage;
    private String nameImage;
    private String email;
    private List<String> roles;
    private Date createdAt;
    private Date updatedAt;

    public JwtResponse(String token, int jwtExpirationMs, long id, String username, String fullName,
                       Date birthday, int gender, String address, String postcode, String phone, String linkImage,
                       String nameImage, String email, List<String> roles, Date createdAt, Date updatedAt) {
        this.token = token;
        this.jwtExpirationMs = jwtExpirationMs;
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.linkImage = linkImage;
        this.nameImage = nameImage;
        this.email = email;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
