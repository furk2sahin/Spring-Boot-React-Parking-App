package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.VehicleDto;
import com.furkannsahin.parking.model.Vehicle;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    @IterableMapping(qualifiedByName = "toVehicleDto")
    List<VehicleDto> toVehicleDtos(List<Vehicle> vehicles);

    @Named("toVehicle")
    Vehicle toVehicle(VehicleDto vehicleDto);

    @Named("toVehicleDto")
    VehicleDto toVehicleDto(Vehicle vehicle);
}
