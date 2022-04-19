package com.parking.parkingspaces.adapters.external.jpa.adapter.parking;

import com.parking.parkingspaces.adapters.external.entities.ParkingEntity;
import com.parking.parkingspaces.application.port.out.ParkingOut;
import com.parking.parkingspaces.config.exception.DataNotFoundException;
import com.parking.parkingspaces.config.exception.ParkingCustomErrorException;
import com.parking.parkingspaces.config.exception.ParkingSpaceCustomErrorException;
import com.parking.parkingspaces.config.exception.ParkingSpaceNotFoundException;
import com.parking.parkingspaces.domain.Parking;
import lombok.AllArgsConstructor;
import org.hibernate.JDBCException;
import org.hibernate.LazyInitializationException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import static com.parking.parkingspaces.config.utility.Constants.DATA_NOT_FOUND_EXCEPTION;

@Repository
@AllArgsConstructor
public class ParkingAdapter implements ParkingOut {
    private final ModelMapper modelMapper;
    private final ParkingPersistence parkingRepository;

    @Override
    public void create(Parking parking) {
        parkingRepository.save(modelMapper.map(parking, ParkingEntity.class));
    }

    @Override
    public Parking find(int id) {
        return modelMapper.map(parkingRepository.findById(id).orElseThrow(() -> new DataNotFoundException(DATA_NOT_FOUND_EXCEPTION)), Parking.class);
    }

    @Override
    public void update(int id, Parking parking) {
        if (!parkingRepository.existsById(id)) {
            throw new ParkingSpaceNotFoundException(id);
        }

        try {
            ParkingEntity parkingFromDb = parkingRepository.getById(id);
            parkingFromDb.setName(parking.getName());
            parkingFromDb.setAddress(parking.getAddress());
            parkingFromDb.setHourlyPrice(parking.getHourlyPrice());
            parkingFromDb.setSchedules(parking.getSchedules());
            parkingFromDb.setCurrency(parking.getCurrency());
            parkingRepository.save(parkingFromDb);
        } catch (LazyInitializationException e) {
            throw new ParkingCustomErrorException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        if (!parkingRepository.existsById(id)) {
            throw new ParkingSpaceNotFoundException(id);
        }
        try {
            parkingRepository.deleteById(id);
        } catch (JDBCException e) {
            throw new ParkingSpaceCustomErrorException(e.getMessage());
        }

    }
}
