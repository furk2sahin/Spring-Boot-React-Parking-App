package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.ParkingOwner;
import com.furkannsahin.parking.repository.ParkingOwnerRepository;
import com.furkannsahin.parking.service.ParkingOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingOwnerServiceImpl implements ParkingOwnerService {

    ParkingOwnerRepository parkingOwnerRepository;

    @Autowired
    public void setParkingOwnerRepository(ParkingOwnerRepository parkingOwnerRepository) {
        this.parkingOwnerRepository = parkingOwnerRepository;
    }

    @Override
    public List<ParkingOwner> getParkingOwners() {
        return parkingOwnerRepository.findAll();
    }

    @Override
    public ParkingOwner createParkingOwner(ParkingOwner parkingOwner) {
        return parkingOwnerRepository.save(parkingOwner);
    }
}
