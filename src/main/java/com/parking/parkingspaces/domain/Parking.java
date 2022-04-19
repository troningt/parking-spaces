package com.parking.parkingspaces.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    private int id;
    private String name;
    private String address;
    private String schedules;
    private BigInteger hourlyPrice;
    private String currency;
}
