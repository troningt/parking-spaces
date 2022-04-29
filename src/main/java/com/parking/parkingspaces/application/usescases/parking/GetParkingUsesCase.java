package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.GetParkingQueryService;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.parking.parkingspaces.config.utility.Constants.DATA_NOT_FOUND_EXCEPTION;

@Service
@AllArgsConstructor
public class GetParkingUsesCase implements GetParkingQueryService {

    private final ParkingOut parkingOut;

    @Override
    public Parking execute(int id) {
        return parkingOut.find(id).orElseThrow(() -> new DataNotFoundException(DATA_NOT_FOUND_EXCEPTION));
    }
}
