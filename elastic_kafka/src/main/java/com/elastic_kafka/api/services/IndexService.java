package com.elastic_kafka.api.services;

import org.springframework.stereotype.Service;


@Service
public interface IndexService {
	
	public void tryToCreateIndices();
}
