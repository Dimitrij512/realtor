package com.home.realtor.models;

import lombok.Data;

@Data
public class Address {
    private String id;
    private String street;
    private String number;
    private String flat;
    private Region region;
}
