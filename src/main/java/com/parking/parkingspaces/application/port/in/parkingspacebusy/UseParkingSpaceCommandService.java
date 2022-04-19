package com.parking.parkingspaces.application.port.in.parkingspacebusy;

import com.parking.parkingspaces.domain.ParkingSpaceBusy;

public interface UseParkingSpaceCommandService {
    void execute(ParkingSpaceBusy parkingSpaceBusyItem);
}
