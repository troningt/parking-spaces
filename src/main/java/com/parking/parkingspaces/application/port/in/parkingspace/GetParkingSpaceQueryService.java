package com.parking.parkingspaces.application.port.in.parkingspace;

import com.parking.parkingspaces.domain.ParkingSpace;

public interface GetParkingSpaceQueryService {
    ParkingSpace execute(int id);
}

