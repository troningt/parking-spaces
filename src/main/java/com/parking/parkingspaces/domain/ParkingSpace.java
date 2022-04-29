package com.parking.parkingspaces.domain;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@Builder
public class ParkingSpace {
    int id;
    String name;
    boolean isBusy;
}
