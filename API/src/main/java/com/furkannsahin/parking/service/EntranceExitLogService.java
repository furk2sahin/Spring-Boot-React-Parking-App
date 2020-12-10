package com.furkannsahin.parking.service;

import com.furkannsahin.parking.model.EntranceExitLog;

import java.util.List;

public interface EntranceExitLogService {
    List<EntranceExitLog> getEntranceExitLogs();
    EntranceExitLog createEntranceExitLog(EntranceExitLog entranceExitLog);
    EntranceExitLog updateEntranceExitLog(Long id, EntranceExitLog entranceExitLog);
    void deleteEntranceExitLogByVehicleId(Long vehicleId);
}
