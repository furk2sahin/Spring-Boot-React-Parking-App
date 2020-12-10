package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.EntranceExitLogDto;
import com.furkannsahin.parking.mapper.EntranceExitLogMapper;
import com.furkannsahin.parking.model.EntranceExitLog;
import com.furkannsahin.parking.service.EntranceExitLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "EntranceExitLog Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class EntranceExitLogRestController {

    @Autowired
    private final EntranceExitLogService entranceExitLogService;

    private final EntranceExitLogMapper entranceExitLogMapper;

    @ApiOperation(value = "Get all logs", notes ="returns all entrance exit logs")
    @GetMapping("/entrance_exit_log")
    public ResponseEntity<List<EntranceExitLogDto>> getEntranceExitLogs(){
        List<EntranceExitLog> entranceExitLogs = entranceExitLogService.getEntranceExitLogs();
        return ResponseEntity.ok(entranceExitLogMapper.toEntranceExitLogDtos(entranceExitLogs));
    }

    @ApiOperation(value = "Create an entrance exit log", notes ="Creates an entrance exit log and returns it")
    @PostMapping("/entrance_exit_log")
    public ResponseEntity<EntranceExitLogDto> createEntranceExitLog(@RequestBody EntranceExitLogDto entranceExitLogDto){
        try{
            EntranceExitLog entranceExitLog = entranceExitLogService.createEntranceExitLog(entranceExitLogMapper.toEntranceExitLog(entranceExitLogDto));
            return ResponseEntity.ok(entranceExitLogMapper.toEntranceExitLogDto(entranceExitLog));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Update an entrance exit log", notes = "Updates an entrance exit log")
    @PutMapping("/entrance_exit_log/{id}")
    public ResponseEntity<?> updateEntranceExitLog(@PathVariable("id") Long id, @RequestBody EntranceExitLogDto entranceExitLogDto){
        try{
            entranceExitLogService.updateEntranceExitLog(id, entranceExitLogMapper.toEntranceExitLog(entranceExitLogDto));
            return new ResponseEntity<>("Entrance exit log updated with id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Delete an entrance exit log", notes = "Deletes an entrance exit log by given id")
    @DeleteMapping("/entrance_exit_log/{vehicleId}")
    public ResponseEntity<?> deleteEntranceExitLog(@PathVariable("vehicleId") Long vehicleId){
        try{
            entranceExitLogService.deleteEntranceExitLogByVehicleId(vehicleId);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Entrance Exit Logs Successfully deleted by vehicle id: " + vehicleId, HttpStatus.OK);
    }

}
