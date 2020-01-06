package com.home.realtor.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Region {
    @Id
    String id;
    String companyId;
    String name;
}
