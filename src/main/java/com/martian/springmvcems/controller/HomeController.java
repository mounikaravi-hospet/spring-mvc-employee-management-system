package com.martian.springmvcems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.martian.springmvcems.model.Employee;
import com.martian.springmvcems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Employee> empList = empService.getAllEmployees();
		model.addAttribute("empList", empList);
		return "index";
	}
	
	@GetMapping("/empSavePage")
	public String empSave() {
		return "empSave";
	}
	
	@GetMapping("/empEditPage/{id}")
	public String empEdit(@PathVariable Integer id, Model model) {
//		System.out.println(id);
		Employee employee = empService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "empEdit";
	}
	
	@PostMapping("/empSave")
	public String saveEmp(@ModelAttribute Employee employee, HttpSession session) {
//		System.out.println(employee);
		Employee newEmp = empService.saveEmp(employee);
		if(newEmp != null) {
//			System.out.println("Success");
			session.setAttribute("msg", "Registeration successful");
		}else {
//			System.out.println("Failed");
			session.setAttribute("msg", "Registeration unsuccessful");
		}
		return "redirect:/empSavePage";
	}
	
	@PostMapping("/empUpdate")
	public String updateEmp(@ModelAttribute Employee employee, HttpSession session) {
//		System.out.println(employee);
		Employee updateEmp = empService.saveEmp(employee);
		if(updateEmp != null) {
//			System.out.println("Success");
			session.setAttribute("msg", "Updated successfully");
		}else {
//			System.out.println("Failed");
			session.setAttribute("msg", "Updation unsuccessful");
		}
		return "redirect:/";
	}
	
	@GetMapping("/empDelete/{id}")
	public String deleteEmployee(@PathVariable Integer id, HttpSession session) {
		boolean del = empService.deleteEmployee(id);
		if(del) {
			session.setAttribute("msg", " Deleted successfully");
		}else {
			session.setAttribute("msg", " Could not be deleted");
		}
		return "redirect:/";
	}
}
