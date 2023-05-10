package com.elastic_kafka.api.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elastic_kafka.api.model.Employees;


public interface EmployeesRepository extends ElasticsearchRepository<Employees, String>{
	
}
