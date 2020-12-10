package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.ParkingOwnerDto;
import com.furkannsahin.parking.model.ParkingOwner;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkingOwnerMapper {

    @IterableMapping(qualifiedByName = "toParkingOwnerDto")
    List<ParkingOwnerDto> toParkingOwnerDtos(List<ParkingOwner> parkingOwners);

    @Named("toParkingOwnerDto")
    ParkingOwnerDto toParkingOwnerDto(ParkingOwner parkingOwner);

    @Named("toParkingOwner")
    ParkingOwner toParkingOwner(ParkingOwnerDto parkingOwnerDto);

}
