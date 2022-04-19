package com.parking.parkingspaces.application.port.in.parking;

import com.parking.parkingspaces.domain.Parking;

public interface UpdateParkingCommandService {
    void execute(int id, Parking parking);
}
