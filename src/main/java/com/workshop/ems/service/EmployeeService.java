package com.workshop.ems.service;

import com.workshop.ems.entity.Employee;
import com.workshop.ems.model.PageableResponse;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long employeeId);

    PageableResponse<Employee> getAllEmployee(int pageNumber, int pageSize);

    Employee updateEmployee(Long employeeId, Employee employeeInfo);

    void deleteEmployee(Long employeeId);

    void deleteAllEmployee();
}
