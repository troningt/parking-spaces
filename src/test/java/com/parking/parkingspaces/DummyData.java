package com.parking.parkingspaces;


import com.parking.parkingspaces.adapters.controller.dto.ParkingDTO;
import com.parking.parkingspaces.adapters.controller.dto.ParkingSpaceDTO;
import com.parking.parkingspaces.adapters.external.entities.ParkingEntity;

import java.math.BigInteger;

public class DummyData {
    public static ParkingDTO parkingDTO = ParkingDTO.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
    public static ParkingSpaceDTO parkingSpaceDTO = ParkingSpaceDTO.builder().id(1).name("Parking 1").isBusy(false).build();
    static ParkingEntity parkingEntity = ParkingEntity.builder().id(1).name("Parking 1").address("Address").currency("USD").hourlyPrice(BigInteger.valueOf(5)).schedules("24/7").hourlyPrice(BigInteger.valueOf(5)).build();
}
