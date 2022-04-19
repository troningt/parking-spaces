package com.parking.parkingspaces.application.usescases.parkingspace;

import com.parking.parkingspaces.application.port.in.parkingspace.GetParkingSpacesQueryService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import com.parking.parkingspaces.domain.ParkingSpace;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetParkingSpacesUsesCase implements GetParkingSpacesQueryService {
    private final ParkingSpaceOut parkingSpaceRepository;

    @Override
    public List<ParkingSpace> execute() {
        return parkingSpaceRepository.findAll();
    }
}
