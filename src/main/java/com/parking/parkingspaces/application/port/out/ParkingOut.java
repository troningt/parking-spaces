package com.parking.parkingspaces.application.port.out;

import com.parking.parkingspaces.domain.Parking;

import java.util.Optional;

public interface ParkingOut {
    void create(Parking parking);

    Optional<Parking> find(int id);

    void update(int id, Parking parking);

    void delete(int id);
}
