package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.DeleteParkingCommandService;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteParkingUsesCase implements DeleteParkingCommandService {
    private final ParkingOut parkingOut;

    @Override
    public void execute(int id) {
        parkingOut.delete(id);
    }
}
