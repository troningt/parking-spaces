package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.GetParkingQueryService;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetParkingUsesCase implements GetParkingQueryService {

    private final ParkingOut parking;

    @Override
    public Parking execute(int id) {
        return parking.find(id);
    }
}
