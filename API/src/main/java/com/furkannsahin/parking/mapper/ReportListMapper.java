package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.ReportListDto;
import com.furkannsahin.parking.model.ReportList;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportListMapper {

    @IterableMapping(qualifiedByName = "toReportListDto")
    List<ReportListDto> toReportListDtos(List<ReportList> reportLists);

    @Named("toReportList")
    ReportList toReportList(ReportListDto reportListDto);

    @Named("toReportListDto")
    ReportListDto toReportListDto(ReportList reportList);
}
