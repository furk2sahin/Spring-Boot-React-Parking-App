package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.ParkingDto;
import com.furkannsahin.parking.mapper.ParkingMapper;
import com.furkannsahin.parking.model.Parking;
import com.furkannsahin.parking.service.ParkAreaService;
import com.furkannsahin.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Parking Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class ParkingRestController {

    @Autowired
    private final ParkingService parkingService;

    @Autowired
    private final ParkAreaService parkAreaService;

    private final ParkingMapper parkingMapper;

    @ApiOperation(value = "Get all parkings", notes ="returns all parkings")
    @GetMapping("/parking")
    public ResponseEntity<List<ParkingDto>> getParkings(){
        List<Parking> parkings = parkingService.getParkings();
        return ResponseEntity.ok(parkingMapper.toParkingDtos(parkings));
    }

    @ApiOperation(value = "Create an parking", notes ="Creates an parking and returns it")
    @PostMapping("/parking")
    public ResponseEntity<ParkingDto> createParking(@RequestBody ParkingDto parkingDto){
        try{
            Parking createdParking = parkingService.createParking(parkingMapper.toParking(parkingDto));
            parkAreaService.createParkArea(createdParking.getParkAreaCount(), createdParking.getId());
            return ResponseEntity.ok(parkingMapper.toParkingDto(createdParking));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
