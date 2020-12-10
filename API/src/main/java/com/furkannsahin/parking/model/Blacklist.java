package com.furkannsahin.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenBlacklist")
    @SequenceGenerator(name="parkingSeqGenBlacklist", sequenceName = "parking_sequence_blacklist", allocationSize = 1)
    private Long id;

    @CreationTimestamp
    private Date date;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Long parkingId;
}
