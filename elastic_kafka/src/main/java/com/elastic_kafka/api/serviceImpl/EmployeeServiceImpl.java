package com.elastic_kafka.api.serviceImpl;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elastic_kafka.api.Payload.EmployeeReqDto;
import com.elastic_kafka.api.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import java.text.SimpleDateFormat;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private static final String URL = "http://10.11.200.109:9191/elk/api/createEmployee";

	
	@KafkaListener(
		topics = "oracle_topic.ELK_USER.EMPLOYEES",
		groupId = "elasticKafka"
	)

	@Override
	public void consumerService(String eventMessage){
		
		try {
			
		
		JSONObject jsonObject = new JSONObject(eventMessage);
		JSONObject payload = jsonObject.getJSONObject("payload");
		EmployeeReqDto employeeReqDto = new EmployeeReqDto();
        
        if(!payload.isNull("before")) {
        	JSONObject before = payload.getJSONObject("before");
        	long timestamp = before.getLong("HIRE_DATE");
        	// Create a Date object from the timestamp
            Date date = new Date(timestamp);
         // Create a SimpleDateFormat object to define the desired date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         // Format the date as a string
            String dateString = dateFormat.format(date);
              
        	employeeReqDto.setEmployeeId(String.valueOf(before.getInt("EMPLOYEE_ID")));
        	employeeReqDto.setFirstName(before.getString("FIRST_NAME"));
        	employeeReqDto.setLastName(before.getString("LAST_NAME"));
        	employeeReqDto.setEmail(before.getString("EMAIL"));
        	employeeReqDto.setPhoneNumber(before.getString("PHONE_NUMBER"));
        	employeeReqDto.setHireDate(dateString);
        	employeeReqDto.setJobId(before.getString("JOB_ID"));
        	employeeReqDto.setSalary(before.getString("SALARY"));
        	employeeReqDto.setCommissionPct(before.optString("COMMISSION_PCT","null"));
        	employeeReqDto.setManagerId(String.valueOf(before.getInt("MANAGER_ID")));
        	employeeReqDto.setDepartmentId(String.valueOf(before.getString("MANAGER_ID")));
        }else if(!payload.isNull("after")) {
        	JSONObject after = payload.getJSONObject("after");
        	long timestamp = after.getLong("HIRE_DATE");
        	// Create a Date object from the timestamp
            Date date = new Date(timestamp);
         // Create a SimpleDateFormat object to define the desired date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         // Format the date as a string
            String dateString = dateFormat.format(date);
            
        	employeeReqDto.setEmployeeId(String.valueOf(after.getInt("EMPLOYEE_ID")));
        	employeeReqDto.setFirstName(after.getString("FIRST_NAME"));
        	employeeReqDto.setLastName(after.getString("LAST_NAME"));
        	employeeReqDto.setEmail(after.getString("EMAIL"));
        	employeeReqDto.setPhoneNumber(after.getString("PHONE_NUMBER"));
        	employeeReqDto.setHireDate(dateString);
        	employeeReqDto.setJobId(after.getString("JOB_ID"));
        	employeeReqDto.setSalary(after.getString("SALARY"));
        	employeeReqDto.setCommissionPct(after.optString("COMMISSION_PCT","null"));
        	employeeReqDto.setManagerId(String.valueOf(after.getInt("MANAGER_ID")));
        	employeeReqDto.setDepartmentId(String.valueOf(after.getInt("DEPARTMENT_ID")));
        }else {
        	LOGGER.info("No Data Found");
        }
        
     // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
     // Set the request body
        String requestBody = employeeReqDto.toString();
        
     // Create the HttpEntity with headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        
     // Make the POST request
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        
     // Get the response body
        String responseBody = responseEntity.getBody();
		
		LOGGER.info(responseBody);
		}catch (NullPointerException e){
			LOGGER.error("NullPointerException occurred during message processing: {}", e.getMessage());
		}
		
	}

	
}
