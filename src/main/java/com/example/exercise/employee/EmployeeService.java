package com.example.exercise.employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

//Business Logic
@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getEmployee() {
		
		return employeeRepository.findAll();
			
	}

	public void addNewEmployee(Employee employee) {
		
		Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
		
		if(employeeOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		else {
			employeeRepository.save(employee);
		}
		
		
	}

	public void deleteEmployee(Long employeeId) {
		
		employeeRepository.findById(employeeId);
		
		boolean exist = employeeRepository.existsById(employeeId);
		
		if(!exist) {
			throw new IllegalStateException("Employee with Id " + employeeId + " does not exist!");
		}
		
		employeeRepository.deleteById(employeeId);
		
	}

	@Transactional
	public void updateEmployee(Long employeeId, String name, String email) {
		
		Employee emp = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new IllegalStateException("Student with id " + employeeId + "does not exist!"));
		
		if(name != null && name.length() > 0 && !Objects.equals(emp.getName(), name)) {
			emp.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(emp.getEmail(), email)) {
			
			Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(email);
			
			if(employeeOptional.isPresent()) {
				throw new IllegalStateException("Email taken!");
			}
			
			emp.setEmail(email);
		}
		
	}

//	public List<Employee> getEmployee() {
//			
//		return List.of(
//				
//			new Employee (
//					
//					1L,
//					"Abdul",
//					"abdul@gmail.com",
//					LocalDate.of(2000, 04, 16),
//					21
//			)
//		
//		);
//			
//	}
}
