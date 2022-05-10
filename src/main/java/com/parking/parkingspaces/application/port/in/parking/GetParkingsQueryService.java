package com.parking.parkingspaces.application.port.in.parking;

import com.parking.parkingspaces.domain.Parking;

import java.util.List;

public interface GetParkingsQueryService {
    List<Parking> execute();
}
