package com.elastic_kafka.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic_kafka.api.model.Employees;
import com.elastic_kafka.api.services.ConsumerService;

@RestController
@RequestMapping("/api/employees")
public class MainController {
	private ConsumerService consumerService;

	public MainController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}
	
	@PostMapping()
	public void index(@RequestBody final Employees employees) {
		consumerService.save(employees);
	}
	
	
	
}
