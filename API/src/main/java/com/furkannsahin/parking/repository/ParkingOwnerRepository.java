package com.furkannsahin.parking.repository;

import com.furkannsahin.parking.model.ParkingOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingOwnerRepository extends JpaRepository<ParkingOwner, Long> {
}
