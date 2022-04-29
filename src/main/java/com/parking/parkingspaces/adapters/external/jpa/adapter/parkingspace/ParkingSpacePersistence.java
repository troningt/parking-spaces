package com.parking.parkingspaces.adapters.external.jpa.adapter.parkingspace;

import com.parking.parkingspaces.adapters.external.entities.ParkingSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpacePersistence extends JpaRepository<ParkingSpaceEntity, Integer> {
}
