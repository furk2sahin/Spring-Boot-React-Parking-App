package com.furkannsahin.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ParkArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "parkingSeqGenParkArea")
    @SequenceGenerator(name="parkingSeqGenParkArea", sequenceName = "parking_sequence_parkarea", allocationSize = 1)
    private Long id;

    @Column
    private boolean isFull;

    @Column
    private Long vehicleId;

    @Column
    private Long parkingId;
}
