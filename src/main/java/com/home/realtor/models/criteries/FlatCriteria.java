package com.home.realtor.models.criteries;

import com.home.realtor.models.Region;
import com.home.realtor.models.enums.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FlatCriteria {
    int price;
    boolean active;
    List<State> stateList;
    List<Heating> heatingList;
    List<HotWater> hotWaterList;
    List<TypeRooms> typeRoomsList;
    List<TypeBuilding> typeBuildingList;
    List<TypeFurniture> typeFurnitureList;
    List<Region> regionList;

    public List<State> getStateList() {
        return Objects.isNull(this.stateList)
            ? new ArrayList<>()
            : this.stateList;
    }

    public List<Heating> getHeatingList() {
        return Objects.isNull(this.heatingList)
            ? new ArrayList<>()
            : this.heatingList;
    }

    public List<HotWater> getHotWaterList() {
        return Objects.isNull(this.hotWaterList)
            ? new ArrayList<>()
            : this.hotWaterList;
    }

    public List<TypeRooms> getTypeRoomsList() {
        return Objects.isNull(this.typeRoomsList)
            ? new ArrayList<>()
            : this.typeRoomsList;
    }

    public List<TypeFurniture> getTypeFurnitureList() {
        return Objects.isNull(this.typeFurnitureList)
            ? new ArrayList<>()
            : this.typeFurnitureList;
    }

    public List<Region> getRegionList() {
        return Objects.isNull(this.regionList)
            ? new ArrayList<>()
            : this.regionList;
    }

}
