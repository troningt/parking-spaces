package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.CreateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import com.parking.parkingspaces.domain.ParkingSpace;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateParkingSpaceUsesCase implements CreateParkingSpaceCommandService {

    private final ParkingSpaceOut parkingSpaceOut;

    @Override
    public void execute(ParkingSpace parkingSpace) {
        parkingSpaceOut.create(parkingSpace);
    }
}
