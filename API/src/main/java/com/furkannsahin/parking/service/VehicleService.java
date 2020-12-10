package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getVehicles();
    Vehicle createVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);
}
