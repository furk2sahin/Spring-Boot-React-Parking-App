package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.ParkAreaDto;
import com.furkannsahin.parking.model.ParkArea;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParkAreaMapper {

    @IterableMapping(qualifiedByName = "toParkAreaDto")
    List<ParkAreaDto> toParkAreaDtos(List<ParkArea> parkAreas);

    @Named("toParkArea")
    ParkArea toParkArea(ParkAreaDto parkAreaDto);

    @Named("toParkAreaDto")
    ParkAreaDto toParkAreaDto(ParkArea parkArea);
}
