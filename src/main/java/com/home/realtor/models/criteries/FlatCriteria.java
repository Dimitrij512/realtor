package com.home.realtor.models.criteries;

import com.home.realtor.models.enums.*;
import lombok.Data;

import java.util.List;

@Data
public class FlatCriteria {
    int price;
    List<State> stateList;
    List<Heating> heatingList;
    List<HotWater> hotWaterList;
    List<TypeRooms> typeRoomsList;
    List<TypeBuilding> typeBuildingList;
    List<TypeFurniture> typeFurnitureList;
}
