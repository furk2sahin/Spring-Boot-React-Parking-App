package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.ParkArea;

import java.util.List;

public interface ParkAreaService {
    List<ParkArea> getParkAreas();
    void createParkArea(Long areaCount, Long parkingId);
    ParkArea updateParkArea(Long id, ParkArea parkArea);
}
