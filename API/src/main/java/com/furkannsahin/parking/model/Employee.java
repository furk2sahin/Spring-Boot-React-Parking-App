package com.furkannsahin.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="Employees")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSeqGenEmployee")
    @SequenceGenerator(name="parkingSeqGenEmployee", sequenceName = "parking_sequence_employee", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phone;

    @Column
    @UpdateTimestamp
    private Date startingDate;

    @Column
    private boolean isAccepted;

    @Column(nullable = false)
    private Long parkingId;
}
