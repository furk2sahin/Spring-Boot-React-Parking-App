package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.ReportList;

import java.util.List;

public interface ReportListService {
    List<ReportList> getReportList();
    ReportList createReportListItem(ReportList reportList);
    ReportList updateReportListItem(Long id, ReportList reportList);
    void deleteReportListItemByVehicleId(Long vehicleId);
}
