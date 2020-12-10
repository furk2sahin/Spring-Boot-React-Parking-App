package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportListDto {

    private Long id;

    private String reportReason;

    private Date date;

    private boolean isActive;

    private Long parkingId;

    private Long employeeId;

    private Long vehicleId;
}
