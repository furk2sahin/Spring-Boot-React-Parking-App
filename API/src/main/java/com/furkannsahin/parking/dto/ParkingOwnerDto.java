package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingOwnerDto {
    private Long id;
    private String fullName;
    private String userName;
    private String password;
    private String phone;
}
