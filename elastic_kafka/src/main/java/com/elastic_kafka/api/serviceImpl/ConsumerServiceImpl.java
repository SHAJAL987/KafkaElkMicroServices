package com.elastic_kafka.api.serviceImpl;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.elastic_kafka.api.helper.Indices;
import com.elastic_kafka.api.model.Employees;
import com.elastic_kafka.api.repository.EmployeesRepository;
import com.elastic_kafka.api.services.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;


@Service
public class ConsumerServiceImpl implements ConsumerService{
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);
	
	@Autowired
	private final EmployeesRepository employeesRepository;
	
	private final RestHighLevelClient restHighLevelClient;

	public ConsumerServiceImpl(EmployeesRepository employeesRepository, RestHighLevelClient restHighLevelClient
			) {
		this.employeesRepository = employeesRepository;
		this.restHighLevelClient = restHighLevelClient;
	}
	
	
	@KafkaListener(
		topics = "oracle_topic.ELK_USER.EMPLOYEES",
		groupId = "elasticKafka"
	)

	@Override
	public void consumerService(String eventMessage) {
		
		

		String TAG = "Json Persing Response - ";
		JSONParser parser = new JSONParser();
		JSONObject payload = null;
		JSONObject before = null;
		JSONObject after = null;
		LOGGER.info(TAG+ eventMessage);
		
		/*try {
			Object jsonObject = parser.parse(eventMessage);
			JSONObject rootJsonObject = (JSONObject) jsonObject;
			payload = (JSONObject) rootJsonObject.get("payload");
			before = (JSONObject) payload.get("before");
			after = (JSONObject) payload.get("after");
			System.out.println("payload Before: "+before.toString());
			System.out.println("payload After: "+after.toString());
			
			if(payload.containsKey("before")) {
				//before = (JSONObject) payload.get("after");
				//System.out.println("After First Name: "+after.get("FIRST_NAME").toString());
				//System.out.println("Before First Name: "+before.get("FIRST_NAME").toString());
			}else {
				Employees employees = new Employees();
				long Hiredate = Long.parseLong(after.get("HIRE_DATE").toString());
				Instant instant = Instant.ofEpochMilli(Hiredate);
				LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		        String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

				
				employees.setEMPLOYEE_ID(after.get("EMPLOYEE_ID").toString());
				employees.setFIRST_NAME(after.get("FIRST_NAME").toString());
				employees.setLAST_NAME(after.get("LAST_NAME").toString());
				employees.setEMAIL(after.get("EMAIL").toString());
				employees.setPHONE_NO(after.get("PHONE_NO").toString());
				employees.setHIRE_DATE(formattedDate);
				employees.setJOB_ID(after.get("JOB_ID").toString());
				employees.setSALARY(after.get("SALARY").toString());
				employees.setCOMMISSION_PCT(after.get("COMMISSION_PCT").toString());
				employees.setMANAGER_ID(after.get("MANAGER_ID").toString());
				employees.setDEPARTMENT_ID(after.get("DEPARTMENT_ID").toString());
				
				//Boolean res =  index(employees);
				
				LOGGER.info(TAG+ employees.getFIRST_NAME());
			}

		}catch (Exception e) {
			LOGGER.info(TAG+ " Error: "+ e);
		}*/
		
		
	}

	@Override
	public Boolean save(final Employees employees) {
		
		try {
			final String employeesAString = MAPPER.writeValueAsString(employees);
			
			final IndexRequest request = new IndexRequest(Indices.EMPLOYEES_INDEX);
			request.id(employees.getEMPLOYEE_ID());
			request.source(employeesAString, XContentType.JSON);
			
			final IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
			
			return response != null && response.status().equals(RestStatus.CREATED);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return false;
		}
		
	}
	
    public Boolean index(final Employees employees) {
		
		try {
			final String employeesAString = MAPPER.writeValueAsString(employees);
			
			final IndexRequest request = new IndexRequest(Indices.EMPLOYEES_INDEX);
			request.id(employees.getEMPLOYEE_ID());
			request.source(employeesAString, XContentType.JSON);
			
			final IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
			
			return response != null && response.status().equals(RestStatus.CREATED);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return false;
		}
		
	}
    
    
    


	
}
