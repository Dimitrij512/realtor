package com.home.realtor.models.criteries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.home.realtor.models.enums.Heating;
import com.home.realtor.models.enums.HotWater;
import com.home.realtor.models.enums.State;
import com.home.realtor.models.enums.TypeBuilding;
import com.home.realtor.models.enums.TypeFurniture;
import com.home.realtor.models.enums.TypeRooms;

import lombok.Data;

@Data
public class FlatCriteria {
    int price;
    String companyId;
    boolean active;
    List<State> stateList;
    List<Heating> heatingList;
    List<HotWater> hotWaterList;
    List<TypeRooms> typeRoomsList;
    List<TypeBuilding> typeBuildingList;
    List<TypeFurniture> typeFurnitureList;
    List<String> regionNameList;

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

    public List<TypeBuilding> getTypeBuildingList() {
        return Objects.isNull(this.typeBuildingList)
            ? new ArrayList<>()
            : this.typeBuildingList;
    }

    public List<TypeFurniture> getTypeFurnitureList() {
        return Objects.isNull(this.typeFurnitureList)
            ? new ArrayList<>()
            : this.typeFurnitureList;
    }

    public List<String> getRegionList() {
        return Objects.isNull(this.regionNameList)
            ? new ArrayList<>()
            : this.regionNameList;
    }

}
