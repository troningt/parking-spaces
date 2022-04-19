package com.parking.parkingspaces.application.port.in.parkingspace;

import com.parking.parkingspaces.domain.ParkingSpace;

public interface UpdateParkingSpaceCommandService {
    void execute(int id, ParkingSpace parkingSpace);
}
