package com.parking.parkingspaces.application.usescases.parking;

import com.parking.parkingspaces.application.port.in.parking.GetParkingsQueryService;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.parking.parkingspaces.config.utility.Constants.DATA_NOT_FOUND_EXCEPTION;

@Service
@AllArgsConstructor
public class GetParkingsUsesCase implements GetParkingsQueryService {

    private final ParkingOut parkingOut;

    @Override
    public List<Parking> execute() {
        return parkingOut.findAll();
    }
}
