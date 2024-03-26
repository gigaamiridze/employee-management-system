package com.workshop.ems.service.impl;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import com.workshop.ems.mapper.EmployeeMapper;
import com.workshop.ems.model.EmployeeResponse;
import com.workshop.ems.model.PageableResponse;
import com.workshop.ems.repository.EmployeeRepository;
import com.workshop.ems.service.EmployeeService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(Long employeeId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (!employee.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new EmployeeResponse(false, "Employee not found with ID: " + employeeId, null));
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new EmployeeResponse(true, "Employee retrieved successfully", EmployeeMapper.mapToDto(employee.get())));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmployeeResponse(false, "Internal server error", null));
        }
    }

    @Override
    public ResponseEntity<EmployeeResponse> getAllEmployee(int pageNumber, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Employee> employees = employeeRepository.findAll(pageable);
            List<Employee> listOfEmployee = employees.getContent();
            List<EmployeeDto> content = listOfEmployee.stream().map(EmployeeMapper::mapToDto).toList();

            PageableResponse<EmployeeDto> pageableResponse = new PageableResponse<>(
                    content,
                    employees.getNumber(),
                    employees.getSize(),
                    employees.getTotalPages(),
                    employees.getTotalElements(),
                    employees.isFirst(),
                    employees.isLast(),
                    employees.isEmpty()
            );

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new EmployeeResponse(true, "Employees retrieved successfully", pageableResponse));
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new EmployeeResponse(false, "Internal server error", null));
        }
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        try {
            if (isInvalidEmployeeData(employeeDto)) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new EmployeeResponse(false, "Invalid employee data", null));
            }

            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if (!employeeOptional.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new EmployeeResponse(false, "Employee not found with ID: " + employeeId, null));
            }

            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setEmail(employeeDto.getEmail());

            Employee updatedEmployee = employeeRepository.save(employee);
            log.info("Employee updated successfully: {}", updatedEmployee);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new EmployeeResponse(true, "Employee updated successfully", EmployeeMapper.mapToDto(updatedEmployee)));
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
