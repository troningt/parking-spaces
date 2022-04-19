package com.parking.parkingspaces.adapters.external.entities;

import lombok.*;

import javax.persistence.*;
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
    private String name;
    private String address;
    private String schedules;
    private BigInteger hourlyPrice;
    private String currency;

    @Override
    public String toString() {
        return String.format("Parking -> id: %d, name: %s, address: %s, schedules: %s, hourlyPrice: %d, currency: %s",
                this.id, this.name, this.address, this.schedules, this.hourlyPrice, this.currency);
    }
}
