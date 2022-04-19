package com.parking.parkingspaces.config.exception;

public class ParkingCustomErrorException extends RuntimeException {
    public ParkingCustomErrorException() {
        super();
    }

    public ParkingCustomErrorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParkingCustomErrorException(final String message) {
        super(message);
    }

    public ParkingCustomErrorException(final Throwable cause) {
        super(cause);
    }
}
