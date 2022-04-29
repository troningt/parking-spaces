package com.parking.parkingspaces.adapters.external.jpa.adapter.parkingspace;

import com.parking.parkingspaces.adapters.external.entities.ParkingSpaceEntity;
import com.parking.parkingspaces.application.port.out.ParkingSpaceOut;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
import com.parking.parkingspaces.config.exception.ParkingSpaceCustomErrorException;
import com.parking.parkingspaces.config.exception.ParkingSpaceNotFoundException;
import com.parking.parkingspaces.domain.ParkingSpace;
import lombok.AllArgsConstructor;
import org.hibernate.JDBCException;
import org.hibernate.LazyInitializationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.parking.parkingspaces.config.utility.Constants.DATA_NOT_FOUND_EXCEPTION;

@Repository
@AllArgsConstructor
public class ParkingSpaceAdapter implements ParkingSpaceOut {

    private final ParkingSpacePersistence parkingSpaceRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(ParkingSpace parkingSpace) {
        parkingSpaceRepository.save(modelMapper.map(parkingSpace, ParkingSpaceEntity.class));
    }

    @Override
    public List<ParkingSpace> findAll() {
        return parkingSpaceRepository.findAll().stream().map(parkingSpaceEntity ->
                modelMapper.map(parkingSpaceEntity, ParkingSpace.ParkingSpaceBuilder.class).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ParkingSpace> findAllFree() {
        return parkingSpaceRepository.findAll().stream()
                .filter(parkingSpaceEntity -> !parkingSpaceEntity.isBusy())
                .map(parkingSpaceEntity -> modelMapper.map(parkingSpaceEntity, ParkingSpace.ParkingSpaceBuilder.class).build())
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpace find(int id) {
        return modelMapper.map(parkingSpaceRepository.findById(id).orElseThrow(() -> new DataNotFoundException(DATA_NOT_FOUND_EXCEPTION)), ParkingSpace.ParkingSpaceBuilder.class).build();
    }

    @Override
    public void update(int id, ParkingSpace parkingSpace) {
        if (!parkingSpaceRepository.existsById(id)) {
            throw new ParkingSpaceNotFoundException(id);
        }

        try {
            ParkingSpaceEntity parkingSpaceFromDb = parkingSpaceRepository.getById(id);
            parkingSpaceFromDb.setName(parkingSpace.getName());
            parkingSpaceFromDb.setBusy(parkingSpace.isBusy());
            parkingSpaceRepository.save(parkingSpaceFromDb);
        } catch (LazyInitializationException e) {
            throw new ParkingSpaceCustomErrorException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if (!parkingSpaceRepository.existsById(id)) {
            throw new ParkingSpaceNotFoundException(id);
        }
        try {
            parkingSpaceRepository.deleteById(id);
        } catch (JDBCException e) {
            throw new ParkingSpaceCustomErrorException(e.getMessage());
        }

    }

}
