package com.parking.parkingspaces.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpaceBusy {
    private String id;

    private int parkingSpace;
    private Long dateStart;
    private Long dateEnd;
    private String licensePlate;
}
