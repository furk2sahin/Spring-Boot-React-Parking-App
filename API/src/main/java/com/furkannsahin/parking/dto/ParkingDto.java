package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {

    private Long id;

    private String name;

    private Long parkAreaCount;

    private Long ownerId;
}