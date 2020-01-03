package com.home.realtor.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Company {
    @Id
    String id;
    String name;
    List<String> userIdList;
}
