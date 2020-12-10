package com.furkannsahin.parking.mapper;

import com.furkannsahin.parking.dto.EmployeeDto;
import com.furkannsahin.parking.model.Employee;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @IterableMapping(qualifiedByName = "toEmployeeDto")
    List<EmployeeDto> toEmployeeDtos(List<Employee> employees);

    @Named("toEmployee")
    Employee toEmployee(EmployeeDto employeeDto);

    @Named("toEmployeeDto")
    EmployeeDto toEmployeeDto(Employee employee);
}
