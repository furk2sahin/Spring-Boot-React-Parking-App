package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.ParkAreaDto;
import com.furkannsahin.parking.mapper.ParkAreaMapper;
import com.furkannsahin.parking.model.ParkArea;
import com.furkannsahin.parking.service.ParkAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ParkArea Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class ParkAreaRestController {

    @Autowired
    private final ParkAreaService parkAreaService;

    private final ParkAreaMapper parkAreaMapper;

    @ApiOperation(value = "Get all park areas", notes ="returns all park areas")
    @GetMapping("/park_area")
    public ResponseEntity<List<ParkAreaDto>> getParkAreas(){
        List<ParkArea> parkAreas = parkAreaService.getParkAreas();
        return ResponseEntity.ok(parkAreaMapper.toParkAreaDtos(parkAreas));
    }

    @ApiOperation(value = "Update an park area", notes = "Updates an park area")
    @PutMapping("/park_area/{id}")
    public ResponseEntity<?> updateParkArea(@PathVariable("id") Long id, @RequestBody ParkAreaDto parkAreaDto){
        try{
            parkAreaService.updateParkArea(id, parkAreaMapper.toParkArea(parkAreaDto));
            return new ResponseEntity<>("Park area updated with id: " + id, HttpStatus.OK);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
