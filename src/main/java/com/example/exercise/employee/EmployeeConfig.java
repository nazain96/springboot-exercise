package com.example.exercise.employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(EmployeeRepository repository) {
		return args -> {
			
			Employee abdul = new Employee(
					
					"Abdul",
					"abdul@gmail.com",
					LocalDate.of(2000, 04, 16)
			);
					
			Employee malik = new Employee(
					
					"Malik",
					"malik@gmail.com",
					LocalDate.of(2003, 04, 16)
			);
			
			repository.saveAll(
					
					List.of(abdul, malik)
			
			);
		};
	}
}
