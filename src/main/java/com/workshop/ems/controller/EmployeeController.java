package com.workshop.ems.controller;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.entity.Employee;
import com.workshop.ems.model.PageableResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/ems/employee")
public interface EmployeeController {

    @PostMapping("/create")
    ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto);

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId);

    @GetMapping
    ResponseEntity<PageableResponse<Employee>> getAllEmployee(
            @RequestParam(value = "pageNumber", defaultValue = "", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "", required = false) int pageSize
    );

    @PutMapping("/{employeeId}")
    ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long employeeId, @Valid @RequestBody EmployeeDto employeeDto);

    @DeleteMapping("/{employeeId}")
    ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId);

    @DeleteMapping("/delete-all")
    ResponseEntity<Void> deleteAllEmployee();
}
