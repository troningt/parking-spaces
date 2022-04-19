package com.parking.parkingspaces.adapters.external.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parking_spaces")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParkingSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private boolean isBusy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParkingSpaceEntity parkingSpace = (ParkingSpaceEntity) o;
        return Objects.equals(id, parkingSpace.id)
                && Objects.equals(name, parkingSpace.getName())
                && Objects.equals(isBusy, parkingSpace.isBusy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isBusy);
    }
}