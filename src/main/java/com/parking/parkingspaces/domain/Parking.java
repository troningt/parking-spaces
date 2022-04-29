package com.parking.parkingspaces.domain;

import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@Builder
public class Parking {
    int id;
    String name;
    String address;
    String schedules;
    BigInteger hourlyPrice;
    String currency;
}
