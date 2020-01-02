package com.home.realtor.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Address {
    @Id
    private String id;
    private String street;
    private String numberOfBuilding;
    private String numberOfFlat;
    private Region region;
}
