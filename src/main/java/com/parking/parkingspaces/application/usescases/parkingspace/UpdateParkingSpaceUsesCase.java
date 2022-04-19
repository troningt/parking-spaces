package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.UpdateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import com.parking.parkingspaces.domain.ParkingSpace;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateParkingSpaceUsesCase implements UpdateParkingSpaceCommandService {

    private final ParkingSpaceOut parkingSpaceOut;

    @Override
    public void execute(int id, ParkingSpace parkingSpace) {
        parkingSpaceOut.update(id, parkingSpace);
    }
}
