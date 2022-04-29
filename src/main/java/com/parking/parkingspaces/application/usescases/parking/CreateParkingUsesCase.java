package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.CreateParkingCommand;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateParkingUsesCase implements CreateParkingCommand {
    private final ParkingOut parkingOut;

    @Override
    public void execute(Parking parking) {
        parkingOut.create(parking);
    }
}
