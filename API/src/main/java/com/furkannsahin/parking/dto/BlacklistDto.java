package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistDto {

    private Long id;

    private Date date;

    private String licensePlate;

    private Long parkingId;
}
