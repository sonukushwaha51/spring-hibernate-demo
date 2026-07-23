package com.hibernate.demo;

import com.hibernate.demo.entity.Employee;
import com.hibernate.demo.service.EmployeeService;

public class EmployeeApplication {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        Employee employee = new Employee(1001, "Sonu", "IT", 67000.00);
        employeeService.saveEmployee(employee);

        Employee employee1 = employeeService.getEmployee(1001);

        System.out.println(employee1.toString());
    }
}
