package com.parking.parkingspaces.application.port.in.parking;

import com.parking.parkingspaces.domain.Parking;

public interface CreateParkingCommandService {
    void execute(Parking parking);
}
