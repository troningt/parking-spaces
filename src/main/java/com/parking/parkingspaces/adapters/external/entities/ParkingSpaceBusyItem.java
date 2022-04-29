package com.parking.parkingspaces.adapters.external.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Document("space_parking_busy")
@Setter
@Getter
public class ParkingSpaceBusyItem {
    @Id
    private ObjectId id;

    @NotBlank(message = "Parking space is mandatory")
    private String parkingSpace;

    @NotBlank(message = "Date start is mandatory")
    private BigInteger dateStart;

    private BigInteger dateEnd;

    @NotBlank(message = "License plate is mandatory")
    private String licensePlate;
}
