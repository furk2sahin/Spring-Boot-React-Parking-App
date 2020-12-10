package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.ReportList;
import com.furkannsahin.parking.repository.ReportListRepository;
import com.furkannsahin.parking.service.ReportListService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportListServiceImpl implements ReportListService {

    ReportListRepository reportListRepository;

    @Autowired
    public void setReportListRepository(ReportListRepository reportListRepository) {
        this.reportListRepository = reportListRepository;
    }

    @Override
    public List<ReportList> getReportList() {
        return reportListRepository.findAll();
    }

    @Override
    public ReportList createReportListItem(ReportList reportList) {
        return reportListRepository.save(reportList);
    }

    @Override
    public ReportList updateReportListItem(Long id, ReportList reportList) {
        ReportList reportListToUpdate = reportListRepository.getOne(id);
        reportListToUpdate.setActive(reportList.isActive());
        return reportListRepository.save(reportListToUpdate);
    }

    @Override
    public void deleteReportListItemByVehicleId(Long vehicleId) {
        reportListRepository.deleteByVehicleId(vehicleId);
    }
}
