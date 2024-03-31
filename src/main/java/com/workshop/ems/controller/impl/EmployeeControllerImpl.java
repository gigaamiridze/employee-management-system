package com.workshop.ems.controller.impl;

import com.workshop.ems.controller.EmployeeController;
import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import com.workshop.ems.mapper.EmployeeMapper;
import com.workshop.ems.model.PageableResponse;
import com.workshop.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEntity(employeeDto);
        Employee employeeResponse = employeeService.createEmployee(employee);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeMapper.mapToDto(employeeResponse));
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeMapper.mapToDto(employee));
    }

    @Override
    public ResponseEntity<PageableResponse<Employee>> getAllEmployee(int pageNumber, int pageSize) {
        PageableResponse<Employee> pageableResponse = employeeService.getAllEmployee(pageNumber, pageSize);
        return ResponseEntity.ok(pageableResponse);
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeMapper.mapToEntity(employeeDto);
        Employee employeeResponse = employeeService.updateEmployee(employeeId, employee);
        return ResponseEntity.ok(employeeMapper.mapToDto(employeeResponse));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteAllEmployee() {
        employeeService.deleteAllEmployee();
        return ResponseEntity.ok().build();
    }
}
