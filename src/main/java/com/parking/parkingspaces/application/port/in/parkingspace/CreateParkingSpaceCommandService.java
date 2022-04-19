package com.parking.parkingspaces.application.port.in.parkingspace;

import com.parking.parkingspaces.domain.ParkingSpace;

public interface CreateParkingSpaceCommandService {
    void execute(ParkingSpace parkingSpace);
}
