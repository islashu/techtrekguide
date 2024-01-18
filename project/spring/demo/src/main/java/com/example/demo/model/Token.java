package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.model.User;

// This is to tag a token to a user
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Token")

/*
* The reason why users can have multiple tokens is that
* 1. They can sign in from multiple devices
* 2. Different tokens have different expiry dates, so certain sensitive operations should be limited to short timings (e.g. 5 minutes) to reduce security risk. User that are not accessing those sensitive operations can have a longer expiry date (e.g. 1 day)
* */
public class Token {
    @Id
    @GeneratedValue
    public Integer id;

    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
