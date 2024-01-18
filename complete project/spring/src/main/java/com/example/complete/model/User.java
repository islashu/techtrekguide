package com.example.complete.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
// To use builder pattern, we need to add @AllArgsConstructor and @NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "_User")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "EmployeeID")
    private Integer userId;
    @Column(name = "FirstName", length = 50)
    private String firstname;
    @Column(name = "LastName", length = 50)
    private String lastname;
    @Column(name = "Password", length = 255)
    private String password;
    @Column(name = "Age")
    private Integer age;

    @Column(name = "Email", length = 50)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "user")
    private List<InsurancePolicy> insurancePolicies;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }

    public List<InsurancePolicy> getInsurancePolicies() {
        return insurancePolicies;
    }

}
