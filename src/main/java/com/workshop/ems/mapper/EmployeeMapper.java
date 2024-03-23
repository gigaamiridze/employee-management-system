package com.workshop.ems.mapper;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {

    public static Employee mapToEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .build();
    }

    public static EmployeeDto mapToDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
