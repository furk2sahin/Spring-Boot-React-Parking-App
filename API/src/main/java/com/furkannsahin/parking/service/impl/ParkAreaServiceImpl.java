package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.*;
import com.furkannsahin.parking.repository.ParkAreaRepository;
import com.furkannsahin.parking.service.ParkAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkAreaServiceImpl implements ParkAreaService {

    private ParkAreaRepository parkAreaRepository;

    @Autowired
    public void setParkAreaRepository(ParkAreaRepository parkAreaRepository) {
        this.parkAreaRepository = parkAreaRepository;
    }

    @Override
    public List<ParkArea> getParkAreas() {
        return parkAreaRepository.findAll();
    }

    @Override
    public void createParkArea(Long areaCount, Long parkingId) {
        List<ParkArea> parkAreas = new ArrayList<>();

        for(int i = 0; i < areaCount; i++){
            ParkArea parkArea = new ParkArea();
            parkArea.setFull(false);
            parkArea.setParkingId(parkingId);
            parkArea.setVehicleId(0L);
            parkAreas.add(parkArea);

            if(i % 1000 == 0 && i > 0){
                parkAreaRepository.saveAll(parkAreas);
                parkAreas.clear();
            }
        }

        if(!parkAreas.isEmpty()){
            parkAreaRepository.saveAll(parkAreas);
            parkAreas.clear();
        }
    }

    @Override
    public ParkArea updateParkArea(Long id, ParkArea parkArea) {
        ParkArea parkAreaToUpdate = parkAreaRepository.getOne(id);
        parkAreaToUpdate.setVehicleId(parkArea.getVehicleId());
        parkAreaToUpdate.setFull(parkArea.isFull());
        return parkAreaRepository.save(parkAreaToUpdate);
    }
}
