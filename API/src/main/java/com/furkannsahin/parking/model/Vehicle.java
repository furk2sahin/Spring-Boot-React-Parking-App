package com.furkannsahin.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="vehicles")
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenVehicle")
    @SequenceGenerator(name="parkingSeqGenVehicle", sequenceName = "parking_sequence_vehicles", allocationSize = 1 )
    private Long id;

    @Column(nullable=false, unique = true)
    private String licensePlate;

    @Column
    private Long userId;

    @Column
    private Long parkingId;

}
