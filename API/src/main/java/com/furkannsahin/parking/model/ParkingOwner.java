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
public class ParkingOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenOwner")
    @SequenceGenerator(name="parkingSeqGenOwner", sequenceName = "parking_sequence_owners", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phone;
}
