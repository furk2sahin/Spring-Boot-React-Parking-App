package com.furkannsahin.parking.repository;

import com.furkannsahin.parking.model.ReportList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReportListRepository extends JpaRepository<ReportList, Long> {

    @Modifying
    @Transactional
    void deleteByVehicleId(Long vehicleId);
}
