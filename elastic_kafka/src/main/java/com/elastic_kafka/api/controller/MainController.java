package com.elastic_kafka.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/api/")
public class MainController {
	
	@GetMapping("/health")
	public String healthCheckApi() {
		return "API Health is Good.";
	}
}
