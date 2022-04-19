package com.parking.parkingspaces.application.port.in.parkingspace;

import com.parking.parkingspaces.domain.ParkingSpace;

import java.util.List;

public interface GetParkingSpacesFreeQueryService {
    List<ParkingSpace> execute();
}

