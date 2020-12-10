package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.Parking;
import com.furkannsahin.parking.repository.ParkingOwnerRepository;
import com.furkannsahin.parking.repository.ParkingRepository;
import com.furkannsahin.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    ParkingRepository parkingRepository;

    ParkingOwnerRepository parkingOwnerRepository;

    @Autowired
    public void setParkingRepository(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Autowired
    public void setParkingOwnerRepository(ParkingOwnerRepository parkingOwnerRepository) {
        this.parkingOwnerRepository = parkingOwnerRepository;
    }

    @Override
    public List<Parking> getParkings() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking createParking(Parking parking) {
        return parkingRepository.save(parking);
    }
}
