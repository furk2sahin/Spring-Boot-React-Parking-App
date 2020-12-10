package com.furkannsahin.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="ReportList")
@NoArgsConstructor
@AllArgsConstructor
public class ReportList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenReports")
    @SequenceGenerator(name="parkingSeqGenReports", sequenceName = "parking_sequence_reports", allocationSize = 1)
    private Long id;

    @Column
    private String reportReason;

    @CreationTimestamp
    private Date date;

    @Column
    private boolean isActive;

    @Column
    private Long parkingId;

    @Column
    private Long employeeId;

    @Column
    private Long vehicleId;
}
