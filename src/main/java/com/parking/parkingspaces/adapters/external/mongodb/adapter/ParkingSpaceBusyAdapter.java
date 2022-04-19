package com.parking.parkingspaces.adapters.external.mongodb.adapter;

import com.parking.parkingspaces.adapters.external.entities.ParkingSpaceBusyItem;
import com.parking.parkingspaces.application.port.in.parkingspace.GetParkingSpaceQueryService;
import com.parking.parkingspaces.application.port.in.parkingspace.UpdateParkingSpaceCommandService;
import com.parking.parkingspaces.application.port.out.ParkingSpaceBusyOut;
import com.parking.parkingspaces.domain.ParkingSpace;
import com.parking.parkingspaces.domain.ParkingSpaceBusy;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.Instant;

@Repository
@AllArgsConstructor
public class ParkingSpaceBusyAdapter implements ParkingSpaceBusyOut {
    private final ModelMapper modelMapper;
    private final ParkingSpaceBusyPersistence parkingSpaceBusyPersistence;
    private final GetParkingSpaceQueryService getParkingSpaceQueryService;
    private final UpdateParkingSpaceCommandService updateParkingSpaceCommandService;

    @Override
    public void useParkingSpace(ParkingSpaceBusy parkingSpace) {
        ParkingSpace space = getParkingSpaceQueryService.execute(parkingSpace.getParkingSpace());
        space.setBusy(true);
        updateParkingSpaceCommandService.execute(space.getId(), space);
        parkingSpace.setDateStart(Timestamp.from(Instant.now()).getTime());
        parkingSpaceBusyPersistence.save(modelMapper.map(parkingSpace, ParkingSpaceBusyItem.class));
    }

    @Override
    public boolean liberate(String id) {
        return false;
    }
}
