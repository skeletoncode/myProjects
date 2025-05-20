package com.tech.tech_servise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_user")
public class ApplicationUser {

    @Id
    @GeneratedValue
    private long id;


    private String username;


    private String password;


    @ManyToOne
    @JoinColumn(name="role_id")
    private UserRole userRole;
}
