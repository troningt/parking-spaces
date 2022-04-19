package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.DeleteParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteParkingSpaceUsesCase implements DeleteParkingSpaceCommandService {
    private final ParkingSpaceOut parkingSpaceOut;
    @Override
    public void execute(int id) {
        parkingSpaceOut.delete(id);
    }
}
