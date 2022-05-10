package com.parking.parkingspaces.adapters.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDTO {
    private int id;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Address first name is required")
    private String address;
    @NotBlank(message = "Schedules first name is required")
    private String schedules;
    @NotNull(message = "Hourly price is required.")
    private BigInteger hourlyPrice;
    @NotNull(message = "Currency is required.")
    private String currency;
}
