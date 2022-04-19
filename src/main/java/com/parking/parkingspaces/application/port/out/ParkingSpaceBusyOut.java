package com.parking.parkingspaces.application.port.out;

import com.parking.parkingspaces.domain.ParkingSpaceBusy;

public interface ParkingSpaceBusyOut {
    void useParkingSpace(ParkingSpaceBusy parkingSpaceBusy);

    boolean liberate(String id);
}
