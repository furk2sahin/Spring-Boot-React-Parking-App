package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.Parking;

import java.util.List;

public interface ParkingService {
    List<Parking> getParkings();
    Parking createParking(Parking parking);
}
