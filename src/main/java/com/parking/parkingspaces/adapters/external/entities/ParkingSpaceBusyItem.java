package com.parking.parkingspaces.adapters.external.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document("space_parking_busy")
@Setter
@Getter
public class ParkingSpaceBusyItem {
    @Id
    private ObjectId id;

    private String parkingSpace;
    private BigInteger dateStart;
    private BigInteger dateEnd;
    private String licensePlate;
}
