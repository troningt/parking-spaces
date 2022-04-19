package com.parking.parkingspaces.application.port.out;

import com.parking.parkingspaces.domain.ParkingSpace;

import java.util.List;

public interface ParkingSpaceOut {
    void create(ParkingSpace parkingSpace);

    List<ParkingSpace> findAll();

    List<ParkingSpace> findAllFree();

    ParkingSpace find(int id);

    void update(int id, ParkingSpace parkingSpace);

    void delete(int id);
}
