package com.parking.parkingspaces.adapters.external.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Entity
@Table(name = "parking")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Schedules is mandatory")
    private String schedules;

    @NotBlank(message = "Hourly price is mandatory")
    private BigInteger hourlyPrice;

    @NotBlank(message = "Currency is mandatory")
    private String currency;

    @Override
    public String toString() {
        return String.format("Parking -> id: %d, name: %s, address: %s, schedules: %s, hourlyPrice: %d, currency: %s",
                this.id, this.name, this.address, this.schedules, this.hourlyPrice, this.currency);
    }
}
