package com.spring.microservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.NotNull;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
@SuppressWarnings("serial")
@Table(name = "users")
@Entity
public class User implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "username", nullable = false)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "token")
    private String token;

    protected User() {}

    public User(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }
}