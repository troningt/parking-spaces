package com.parking.parkingspaces.config.exception;

public class ParkingSpaceCustomErrorException extends RuntimeException {
    public ParkingSpaceCustomErrorException() {
        super();
    }

    public ParkingSpaceCustomErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParkingSpaceCustomErrorException(final String message) {
        super(message);
    }

    public ParkingSpaceCustomErrorException(final Throwable cause) {
        super(cause);
    }
}
