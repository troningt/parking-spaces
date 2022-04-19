package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.UseParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceBusyOut;
import com.parking.parkingspaces.domain.ParkingSpaceBusy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UseParkingSpaceUsesCase implements UseParkingSpaceCommandService {

    private final ParkingSpaceBusyOut parkingSpaceBusyOut;

    @Override
    public void execute(ParkingSpaceBusy parkingSpaceBusy) {
        parkingSpaceBusyOut.useParkingSpace(parkingSpaceBusy);
    }
}
