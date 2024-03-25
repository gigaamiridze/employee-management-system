package com.workshop.ems.controller;

import com.workshop.ems.dto.EmployeeDto;
import com.workshop.ems.model.EmployeeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/ems/employee")
public interface EmployeeController {

    @PostMapping("/create")
    ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeDto employeeDto);

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long employeeId);

    @GetMapping
    ResponseEntity<EmployeeResponse> getAllEmployee(
            @RequestParam(value = "pageNumber", defaultValue = "", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "", required = false) int pageSize
    );
}
