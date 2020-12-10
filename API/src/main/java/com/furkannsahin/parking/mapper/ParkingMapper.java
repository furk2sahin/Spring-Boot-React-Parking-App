package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.ParkingDto;
import com.furkannsahin.parking.model.Parking;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @IterableMapping(qualifiedByName = "toParkingDto")
    List<ParkingDto> toParkingDtos(List<Parking> parkings);

    @Named("toParkingDto")
    ParkingDto toParkingDto(Parking parking);

    @Named("toParking")
    Parking toParking(ParkingDto parkingDto);
}
