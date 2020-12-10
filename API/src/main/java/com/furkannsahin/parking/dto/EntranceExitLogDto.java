package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntranceExitLogDto {

    private Long id;

    private Date entranceTime;

    private Date exitTime;

    private Long parkAreaNumber;

    private Long parkingId;

    private Long vehicleId;
}
