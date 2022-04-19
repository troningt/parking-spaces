package com.parking.parkingspaces.application.port.out;

import com.parking.parkingspaces.domain.Parking;

public interface ParkingOut {
    void create(Parking parking);

    Parking find(int id);

    void update(int id, Parking parking);

    void delete(int id);
}
