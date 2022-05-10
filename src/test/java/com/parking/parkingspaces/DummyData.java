package com.parking.parkingspaces;


import com.parking.parkingspaces.adapters.controller.dto.ParkingDTO;
import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceDTO;
import com.parking.parkingspaces.adapters.external.entities.ParkingEntity;
import com.parking.parkingspaces.adapters.external.entities.ParkingSpaceEntity;
import com.parking.parkingspaces.domain.Parking;
import com.parking.parkingspaces.domain.ParkingSpace;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DummyData {
    public static ParkingSpace.ParkingSpaceBuilder parkingSpace = ParkingSpace.builder().id(1).name("Space 1").isBusy(false);
    public static Parking.ParkingBuilder parking = Parking.builder().id(1).name("Parking 1");
    public static ParkingDTO parkingDTO = ParkingDTO.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
    public static ParkingDTO parkingDTOWithNullField = ParkingDTO.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(null).build();
    public static ParkingSpaceDTO parkingSpaceDTO = ParkingSpaceDTO.builder().id(1).name("Parking 1").isBusy(false).build();
    public static ParkingSpaceDTO parkingSpaceDTOWithNullField = ParkingSpaceDTO.builder().id(1).name(null).isBusy(false).build();
    static ParkingEntity parkingEntity = ParkingEntity.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();

    public static List<ParkingEntity> parkingEntity() {
        List<ParkingEntity> listParkingEntity = new ArrayList<>();
        ParkingEntity task = ParkingEntity.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
        listParkingEntity.add(task);
        task = ParkingEntity.builder().id(2).name("Parking 2").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
        listParkingEntity.add(task);
        task = ParkingEntity.builder().id(3).name("Parking 3").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
        listParkingEntity.add(task);
        return listParkingEntity;
    }

    public static List<ParkingSpaceEntity> parkingSpaceEntity() {
        List<ParkingSpaceEntity> listParkingSpaceEntity = new ArrayList<>();
        listParkingSpaceEntity.add(ParkingSpaceEntity.builder().id(1).name("Parking 1").isBusy(false).build());
        listParkingSpaceEntity.add(ParkingSpaceEntity.builder().id(1).name("Parking 2").isBusy(false).build());
        listParkingSpaceEntity.add(ParkingSpaceEntity.builder().id(1).name("Parking 3").isBusy(false).build());
        return listParkingSpaceEntity;
    }
}
