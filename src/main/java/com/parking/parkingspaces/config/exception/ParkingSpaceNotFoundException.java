package com.parking.parkingspaces.config.exception;

public class ParkingSpaceNotFoundException extends RuntimeException {
    public ParkingSpaceNotFoundException(int id) {
        super(String.format("Parking space with Id %d not found", id));
    }
}