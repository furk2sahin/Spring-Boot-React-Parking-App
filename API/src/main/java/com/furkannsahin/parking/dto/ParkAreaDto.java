package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkAreaDto {

    private Long id;

    private boolean isFull;

    private Long vehicleId;

    private Long parkingId;
}
