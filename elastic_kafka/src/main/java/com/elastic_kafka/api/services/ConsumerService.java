package com.elastic_kafka.api.services;

import com.elastic_kafka.api.model.Employees;

public interface ConsumerService {
	public void consumerService(String eventMessage);
	public Boolean save(final Employees employees);
}
