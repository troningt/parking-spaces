package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.UpdateParkingCommandService;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateParkingUsesCase implements UpdateParkingCommandService {
    private final ParkingOut parkingOut;

    @Override
    public void execute(int id, Parking parking) {
        parkingOut.update(id, parking);
    }
}
