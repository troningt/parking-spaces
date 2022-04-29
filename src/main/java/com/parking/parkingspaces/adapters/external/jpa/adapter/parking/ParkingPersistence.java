package com.parking.parkingspaces.adapters.external.jpa.adapter.parking;

import com.parking.parkingspaces.adapters.external.entities.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingPersistence extends JpaRepository<ParkingEntity, Integer> {
}
