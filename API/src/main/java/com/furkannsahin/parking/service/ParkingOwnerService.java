package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.ParkingOwner;

import java.util.List;

public interface ParkingOwnerService {
    List<ParkingOwner> getParkingOwners();
    ParkingOwner createParkingOwner(ParkingOwner parkingOwner);
}
