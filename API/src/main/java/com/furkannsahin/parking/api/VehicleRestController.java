package com.furkannsahin.parking.api;

import com.furkannsahin.parking.dto.VehicleDto;
import com.furkannsahin.parking.mapper.VehicleMapper;
import com.furkannsahin.parking.model.Vehicle;
import com.furkannsahin.parking.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Vehicle Controller")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "https://reactbootcamp66-bitirme-odevi-furk2sahin.netlify.app"})
public class VehicleRestController {

    @Autowired
    private final VehicleService vehicleService;

    private final VehicleMapper vehicleMapper;

    @ApiOperation(value = "Get all vehicles", notes ="returns all vehicles")
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getVehicles(){
        List<Vehicle> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicleMapper.toVehicleDtos(vehicles));
    }

    @ApiOperation(value = "Create an vehicle", notes ="Creates an vehicle and returns it")
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto){
        try{
            Vehicle createdVehicle = vehicleService.createVehicle(vehicleMapper.toVehicle(vehicleDto));
            return ResponseEntity.ok(vehicleMapper.toVehicleDto(createdVehicle));
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @ApiOperation(value = "Delete an vehicle", notes = "Deletes an vehicle by given id")
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long id){
        try{
            vehicleService.deleteVehicle(id);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>("Vehicle Successfully deleted by id: " + id, HttpStatus.OK);
    }
}
