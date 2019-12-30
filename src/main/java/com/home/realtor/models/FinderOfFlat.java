package com.home.realtor.models;

import com.home.realtor.models.enums.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

@Data
public class FinderOfFlat {
    @Id
    private String id;
    private String phone;
    private String note;
    private List<State> state;
    private List<Region> regionList;
    private List<Heating> heating;
    private List<HotWater> hotWater;
    private List<TypeRooms> typeRooms;
    private List<Utilities> utilities;
    private List<TypeFurniture> typeFurniture;
    private List<TypeBuilding> typeBuilding;
    private List<Equipment> equipmentList;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private int price;
    private boolean active;
}
