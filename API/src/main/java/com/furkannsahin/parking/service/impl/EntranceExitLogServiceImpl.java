package com.furkannsahin.parking.service.impl;

import com.furkannsahin.parking.model.EntranceExitLog;
import com.furkannsahin.parking.repository.EntranceExitLogRepository;
import com.furkannsahin.parking.service.EntranceExitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntranceExitLogServiceImpl implements EntranceExitLogService {

    private EntranceExitLogRepository entranceExitLogRepository;

    @Autowired
    public void setEntranceExitLogRepository(EntranceExitLogRepository entranceExitLogRepository) {
        this.entranceExitLogRepository = entranceExitLogRepository;
    }

    @Override
    public List<EntranceExitLog> getEntranceExitLogs() {
        return entranceExitLogRepository.findAll();
    }

    @Override
    public EntranceExitLog createEntranceExitLog(EntranceExitLog entranceExitLog) {
        return entranceExitLogRepository.save(entranceExitLog);
    }

    @Override
    public EntranceExitLog updateEntranceExitLog(Long id, EntranceExitLog entranceExitLog) {
        EntranceExitLog entranceExitLogToUpdate = entranceExitLogRepository.getOne(id);
        entranceExitLogToUpdate.setExitTime(entranceExitLog.getExitTime());
        return entranceExitLogRepository.save(entranceExitLogToUpdate);
    }

    @Override
    public void deleteEntranceExitLogByVehicleId(Long vehicleId) {
        entranceExitLogRepository.deleteByVehicleId(vehicleId);
    }
}
