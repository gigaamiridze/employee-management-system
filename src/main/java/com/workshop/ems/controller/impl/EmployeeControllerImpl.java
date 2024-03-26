package com.workshop.ems.controller.impl;

import com.workshop.ems.controller.EmployeeController;
import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.model.EmployeeResponse;
import com.workshop.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeControllerImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeDto employeeDto) {
        return employeeService.createEmployee(employeeDto);
    }

    @Override
    public ResponseEntity<EmployeeResponse> getEmployeeById(Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @Override
    public ResponseEntity<EmployeeResponse> getAllEmployee(int pageNumber, int pageSize) {
        return employeeService.getAllEmployee(pageNumber, pageSize);
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeId, employeeDto);
    }

    @Override
    public ResponseEntity<EmployeeResponse> deleteEmployee(Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @Override
    public ResponseEntity<EmployeeResponse> deleteAllEmployee() {
        return employeeService.deleteAllEmployee();
    }
}
