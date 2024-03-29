package com.workshop.ems.service;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.model.EmployeeResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<EmployeeResponse> createEmployee(EmployeeDto employeeDto);

    ResponseEntity<EmployeeResponse> getEmployeeById(Long employeeId);

    ResponseEntity<EmployeeResponse> getAllEmployee(int pageNumber, int pageSize);

    ResponseEntity<EmployeeResponse> updateEmployee(Long employeeId, EmployeeDto employeeDto);

    ResponseEntity<EmployeeResponse> deleteEmployee(Long employeeId);

    ResponseEntity<EmployeeResponse> deleteAllEmployee();
}
