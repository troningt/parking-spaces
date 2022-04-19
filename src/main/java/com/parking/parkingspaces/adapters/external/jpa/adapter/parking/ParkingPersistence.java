package com.parking.parkingspaces.adapters.external.jpa.adapter.parking;

import com.parking.parkingspaces.adapters.external.entities.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ParkingPersistence extends JpaRepository<ParkingEntity, Integer> {
}
