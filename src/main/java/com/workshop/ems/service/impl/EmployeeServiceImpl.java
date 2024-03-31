package com.workshop.ems.service.impl;

import com.workshop.ems.entity.Employee;
import com.workshop.ems.exception.ResourceNotFoundException;
import com.workshop.ems.model.PageableResponse;
import com.workshop.ems.repository.EmployeeRepository;
import com.workshop.ems.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee created successfully: {}", savedEmployee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
        return employee.get();
    }

    @Override
    public PageableResponse<Employee> getAllEmployee(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> content = employees.getContent();

        PageableResponse<Employee> pageableResponse = new PageableResponse<>(
                content,
                employees.getNumber(),
                employees.getSize(),
                employees.getTotalPages(),
                employees.getTotalElements(),
                employees.isFirst(),
                employees.isLast(),
                employees.isEmpty()
        );

        return pageableResponse;
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employeeInfo) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }

        Employee employee = employeeOptional.get();
        employee.setFirstName(employeeInfo.getFirstName());
        employee.setLastName(employeeInfo.getLastName());
        employee.setEmail(employeeInfo.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Employee updated successfully: {}", updatedEmployee);
        return updatedEmployee;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
        log.info("Employee deleted successfully: {}", employee.get());
    }

    @Override
    public void deleteAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found to delete");
        }
        employeeRepository.deleteAll();
        log.info("All employee deleted successfully");
    }
}
