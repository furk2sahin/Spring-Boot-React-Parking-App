package com.furkannsahin.parking.repository;

import com.furkannsahin.parking.model.ParkArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkAreaRepository extends JpaRepository<ParkArea, Long> {
}
