package com.parking.parkingspaces.config.exception;

public class ParkingCreationLimitException extends RuntimeException {
    public ParkingCreationLimitException(String msg) {
        super(msg);
    }
}
