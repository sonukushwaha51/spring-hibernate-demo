package com.hibernate.demo.service;

import com.hibernate.demo.entity.Employee;
import com.hibernate.demo.repository.EmployeeRepository;

public class EmployeeService {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    public void saveEmployee(Employee employee) {
        employeeRepository.saveEmployee(employee);
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
