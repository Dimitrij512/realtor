package com.home.realtor.models;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    @Id
    String id;
    String name;
    List<String> userIdList;
}
