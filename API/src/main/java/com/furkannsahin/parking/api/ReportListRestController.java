package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.ReportListDto;
import com.furkannsahin.parking.mapper.ReportListMapper;
import com.furkannsahin.parking.model.ReportList;
import com.furkannsahin.parking.service.ReportListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ReportList Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class ReportListRestController {

    @Autowired
    private final ReportListService reportListService;

    private final ReportListMapper reportListMapper;

    @ApiOperation(value = "Get report list", notes ="returns report list")
    @GetMapping("/report_list")
    public ResponseEntity<List<ReportListDto>> getReportList(){
        List<ReportList> reportList = reportListService.getReportList();
        return ResponseEntity.ok(reportListMapper.toReportListDtos(reportList));
    }

    @ApiOperation(value = "Create an report list item", notes ="Creates an report list item and returns it")
    @PostMapping("/report_list")
    public ResponseEntity<ReportListDto> createReportListItem(@RequestBody ReportListDto reportListDto){
        try{
            ReportList createdReportList = reportListService.createReportListItem(reportListMapper.toReportList(reportListDto));
            return ResponseEntity.ok(reportListMapper.toReportListDto(createdReportList));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Update an report list item", notes = "Updates an report list item")
    @PutMapping("/report_list/{id}")
    public ResponseEntity<?> updateReportListItem(@PathVariable("id") Long id, @RequestBody ReportListDto reportListDto){
        try{
            reportListService.updateReportListItem(id, reportListMapper.toReportList(reportListDto));
            return new ResponseEntity<>("Report list item updated with id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "Delete report list item", notes = "Deletes report list items by given vehicle id")
    @DeleteMapping("/report_list/{vehicleId}")
    public ResponseEntity<?> deleteReportListItem(@PathVariable("vehicleId") Long vehicleId){
        try{
            reportListService.deleteReportListItemByVehicleId(vehicleId);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Report list items successfully deleted by vehicle id: " + vehicleId, HttpStatus.OK);
    }
}
