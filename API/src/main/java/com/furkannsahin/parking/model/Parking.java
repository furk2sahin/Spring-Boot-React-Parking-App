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
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenParking")
    @SequenceGenerator(name="parkingSeqGenParking", sequenceName = "parking_sequence_parking", allocationSize = 1)
    private Long id;

    @Column
    private String name;

    @Column
    private Long parkAreaCount;

    @Column
    private Long ownerId;
}