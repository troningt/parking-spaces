package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.GetParkingSpaceQueryService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import com.parking.parkingspaces.domain.ParkingSpace;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetParkingSpaceUsesCase implements GetParkingSpaceQueryService {
    private final ParkingSpaceOut parkingSpaceOut;

    @Override
    public ParkingSpace execute(int id) {
        return parkingSpaceOut.find(id);
    }
}
