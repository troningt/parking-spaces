package com.parking.parkingspaces.adapters.external.mongodb.adapter;

import com.parking.parkingspaces.adapters.external.entities.ParkingSpaceBusyItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ParkingSpaceBusyPersistence extends MongoRepository<ParkingSpaceBusyItem, String> {
    @Query("{parkingSpace:'?0'}")
    ParkingSpaceBusyItem findItemByParkingSpace(int parkingSpace);
}
