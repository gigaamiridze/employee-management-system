package com.workshop.ems.mapper;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class EmployeeMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    public abstract Employee mapToEntity(EmployeeDto employeeDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    public abstract EmployeeDto mapToDto(Employee employee);
}
