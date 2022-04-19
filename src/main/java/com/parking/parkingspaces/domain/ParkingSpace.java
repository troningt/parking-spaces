package com.parking.parkingspaces.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpace {
    private int id;
    private String name;
    private boolean isBusy;
}
