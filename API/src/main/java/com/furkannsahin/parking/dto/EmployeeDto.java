package com.furkannsahin.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String fullName;

    private String userName;

    private String password;

    private String phone;

    private Date startingDate;

    private boolean isAccepted;

    private Long parkingId;
}
