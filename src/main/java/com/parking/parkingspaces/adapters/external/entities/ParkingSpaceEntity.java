package com.parking.parkingspaces.adapters.external.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "parking_spaces")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParkingSpaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "State is mandatory")
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