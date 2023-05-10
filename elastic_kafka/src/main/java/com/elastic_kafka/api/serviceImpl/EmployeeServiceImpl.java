package com.elastic_kafka.api.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.elastic_kafka.api.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	@KafkaListener(
		topics = "oracle_topic.ELK_USER.EMPLOYEES",
		groupId = "elasticKafka"
	)

	@Override
	public void consumerService(String eventMessage){

		LOGGER.info(eventMessage);
		
		System.out.print(false);
		
	}
	
}
