package com.workshop.ems.service.impl;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import com.workshop.ems.mapper.EmployeeMapper;
import com.workshop.ems.model.EmployeeResponse;
import com.workshop.ems.repository.EmployeeRepository;
import com.workshop.ems.service.EmployeeService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeDto employeeDto) {
        try {
            if (isInvalidEmployeeData(employeeDto)) {
                return ResponseEntity.badRequest().body(new EmployeeResponse(false, "Invalid employee data", null));
            }

            Employee employee = EmployeeMapper.mapToEntity(employeeDto);
            Employee savedEmployee = employeeRepository.save(employee);
            log.info("Employee created successfully: {}", savedEmployee);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new EmployeeResponse(true, "Employee created successfully", EmployeeMapper.mapToDto(savedEmployee)));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmployeeResponse(false, "Internal server error", null));
        }
    }

    private boolean isInvalidEmployeeData(EmployeeDto employeeDto) {
        return StringUtils.isEmpty(employeeDto.getFirstName()) || StringUtils.isEmpty(employeeDto.getLastName()) || StringUtils.isEmpty(employeeDto.getEmail());
    }
}
