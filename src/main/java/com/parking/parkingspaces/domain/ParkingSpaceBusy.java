package com.parking.parkingspaces.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@Builder
public class ParkingSpaceBusy {
    String id;

    int parkingSpace;
    Long dateStart;
    Long dateEnd;
    String licensePlate;
}
