package com.parking.parkingspaces.config.utility;

public class Constants {
    public static final String MSG_CRUD_PARKING_SPACE = "Parking space %s successfully";
    public static final String MSG_CREATE_OK = String.format(MSG_CRUD_PARKING_SPACE, "created");
    public static final String MSG_DELETE_OK = String.format(MSG_CRUD_PARKING_SPACE, "deleted");
    public static final String MSG_UPDATE_OK =String.format(MSG_CRUD_PARKING_SPACE, "updated");
    public static final String MSG_USE_PARKING_SPACE = "Use of parking space started";
    public static final String MSG_LIBERATE_PARKING_SPACE = "Parking space liberated successfully";

    public static final String MSG_CRUD_PARKING = "Parking %s successfully";
    public static final String MSG_CREATE_PARKING_OK = String.format(MSG_CRUD_PARKING, "created");
    public static final String MSG_DELETE_PARKING_OK = String.format(MSG_CRUD_PARKING, "deleted");
    public static final String MSG_UPDATE_PARKING_OK =String.format(MSG_CRUD_PARKING, "updated");

    public static final String MSG_PARKING_SPACE_ALREADY_USE = "This parking space is already in use";

    public static final String DATA_NOT_FOUND_EXCEPTION = "Data required not found";

    private Constants() {
        throw new IllegalStateException("Utility class never is called");
    }
}
