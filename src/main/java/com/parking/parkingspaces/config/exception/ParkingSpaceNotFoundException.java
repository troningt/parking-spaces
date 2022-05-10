package com.parking.parkingspaces.config.exception;

import com.parking.parkingspaces.config.utility.Constants;

public class ParkingSpaceNotFoundException extends RuntimeException {
    public ParkingSpaceNotFoundException(int id) {
        super(String.format(Constants.PARKING_NOT_FOUND, id));
    }
}