package com.home.realtor.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.home.realtor.models.enums.Heating;
import com.home.realtor.models.enums.HotWater;
import com.home.realtor.models.enums.Lodger;
import com.home.realtor.models.enums.State;
import com.home.realtor.models.enums.TypeBuilding;
import com.home.realtor.models.enums.TypeFurniture;
import com.home.realtor.models.enums.TypeRooms;
import com.home.realtor.models.enums.Utilities;

import lombok.Data;

@Data
public class Flat {
    @Id
    private String id;
    private String companyId;
    private String phone;
    private String note;
    private State state;
    private Address address;
    private Heating heating;
    private HotWater hotWater;
    private TypeRooms typeRooms;
    private Utilities utilities;
    private TypeFurniture typeFurniture;
    private TypeBuilding typeBuilding;
    private List<Equipment> equipmentList;
    private List<Lodger> lodgerList;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int price;
    private boolean active;
}
