package com.tech.tech_servise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public enum RoleType{

        ROLE_USER,

        ROLE_ADMIN,

        ROLE_OPERATOR
    }



}
