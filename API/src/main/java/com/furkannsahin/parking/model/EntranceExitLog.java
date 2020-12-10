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
public class EntranceExitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenEntranceExitLogs")
    @SequenceGenerator(name="parkingSeqGenEntranceExitLogs", sequenceName = "parking_sequence_entrance_exit_logs", allocationSize = 1)
    private Long id;

    @Column(updatable = false)
    @CreationTimestamp
    private Date entranceTime;

    @Column
    private Date exitTime;

    @Column
    private Long parkAreaNumber;

    @Column(nullable = false)
    private Long parkingId;

    @Column
    private Long vehicleId;
}
