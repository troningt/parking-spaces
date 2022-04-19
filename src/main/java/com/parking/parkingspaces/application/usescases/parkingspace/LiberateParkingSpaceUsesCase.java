package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspacebusy.LiberateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceBusyOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LiberateParkingSpaceUsesCase implements LiberateParkingSpaceCommandService {

    private final ParkingSpaceBusyOut parkingSpaceBusyOut;

    @Override
    public void execute(String id) {
        parkingSpaceBusyOut.liberate(id);
    }
}
