package com.elasticService.api.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elasticService.api.model.Employees;
import com.elasticService.api.repository.EmployeeRepository;

@RestController
@RequestMapping("/elk/api")
public class EmployeeController {
	public EmployeeRepository employeeRepository;
	
	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@PostMapping("/createEmployee")
	public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees) throws IOException{
		Employees employeeResponse = employeeRepository.createOrUpdateDocument(employees);
		return new ResponseEntity<>(employeeResponse,HttpStatus.OK);
	}
	
	@GetMapping("/getDocument/{employeeId}")
    public ResponseEntity<Object> getDocumentById(@PathVariable String employeeId) throws IOException {
       Employees product =  employeeRepository.getDocumentById(employeeId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
	
	@DeleteMapping("/deleteDocument/{employeeId}")
    public ResponseEntity<Object> deleteDocumentById(@PathVariable String employeeId) throws IOException {
        String response =  employeeRepository.deleteDocumentById(employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping("/searchDocument")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<Employees> employees = employeeRepository.searchAllDocuments();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
	
	
}
