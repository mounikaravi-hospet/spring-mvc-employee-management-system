package com.martian.springmvcems.service;

import java.util.List;

import com.martian.springmvcems.model.Employee;

public interface EmployeeService {
	public Employee saveEmp(Employee emp);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(Integer id);
	public boolean deleteEmployee(Integer id);
}
