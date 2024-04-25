package com.martian.springmvcems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.martian.springmvcems.model.Employee;
import com.martian.springmvcems.repository.EmployeeRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	
	@Override
	public Employee saveEmp(Employee emp) {
		Employee newEmp = empRepo.save(emp);
		return newEmp;
	}

	@Override
	public List<Employee> getAllEmployees() {		
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(Integer id) {		
		return empRepo.findById(id).get();
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		Employee emp = empRepo.findById(id).get();
		if(emp != null) {
			empRepo.delete(emp);
			return true;
		}
		return false;
	}
	
	public void removeSessionMsg() {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}
	
}
