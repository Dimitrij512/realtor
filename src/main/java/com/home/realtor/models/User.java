package com.home.realtor.models;

import org.springframework.data.annotation.Id;

import com.home.realtor.models.enums.Role;

import lombok.Data;

@Data
public class User {
    @Id
    String id;
    String companyId;
    String name;
    String sureName;
    String email;
    String password;
    Role role;
}
