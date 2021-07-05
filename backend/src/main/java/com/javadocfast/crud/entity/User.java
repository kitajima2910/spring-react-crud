package com.javadocfast.crud.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String fullName;

    @Temporal(TemporalType.DATE)
    private Date birthday; // 1995-10-29

    private int gender; // 0: Nam, 1: Nữ, 3: Khác
    private String address;
    private String postcode;
    private String phone;
    private String linkImage;
    private String email;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @CreationTimestamp
    private Date createAt;

    @CreationTimestamp
    private Date updateAt;

    private Date deleteAt;

    public User(String username, String fullName, Date birthday, int gender, String address, String postcode,
                String phone, String linkImage, String email, String password, Set<Role> roles) {
        this.username = username;
        this.fullName = fullName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.linkImage = linkImage;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
