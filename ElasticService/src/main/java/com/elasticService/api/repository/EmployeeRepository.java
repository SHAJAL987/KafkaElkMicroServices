package com.elasticService.api.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.elasticService.api.model.Employees;
import com.elasticService.api.others.Indecies;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

@Repository
public class EmployeeRepository {
	@Autowired
    private ElasticsearchClient elasticsearchClient;
	
	public Employees createOrUpdateDocument(Employees employees) throws IOException {

		Employees employeeResponse = null;
        IndexResponse response = elasticsearchClient.index(i -> i
                .index(Indecies.INDE_EMPLOYEES)
                .id(employees.getEmployeeId())
                .document(employees)
        );
        if(response.result().name().equals("Created")){
            //return new StringBuilder("Document has been successfully created.").toString();
        	GetResponse<Employees> responseData = elasticsearchClient.get(g -> g
                    .index(Indecies.INDE_EMPLOYEES)
                    .id(employees.getEmployeeId()),
            Employees.class
        			);
        	if(responseData.found()) {
        		employeeResponse = responseData.source();
        		System.out.println("Employee First Name: "+employeeResponse.getFirstName());
        	}else {
        		System.out.println("Employee Not found");
        	}
        	
        }else if(response.result().name().equals("Updated")){
            //return new StringBuilder("Document has been successfully updated.").toString();
        	GetResponse<Employees> responseData = elasticsearchClient.get(g -> g
                    .index(Indecies.INDE_EMPLOYEES)
                    .id(employees.getEmployeeId()),
            Employees.class
        			);
        	if(responseData.found()) {
        		employeeResponse = responseData.source();
        		System.out.println("Employee Updated Successfully.");
        	}else {
        		System.out.println("Employee Not found");
        	}
        	
        }
        return employeeResponse;
        		//new StringBuilder("Error while performing the operation.").toString();
    }
	
	public Employees getDocumentById(String employeeId) throws IOException {
		Employees employeeResponse = null;
		GetResponse<Employees> responseData = elasticsearchClient.get(g -> g
                .index(Indecies.INDE_EMPLOYEES)
                .id(employeeId),
        Employees.class
    			);
		
    	if(responseData.found()) {
    		employeeResponse = responseData.source();
    		System.out.println("Employee First Name: "+employeeResponse.getFirstName());
    	}else {
    		System.out.println("Employee Not found");
    	}
    	return employeeResponse;
	}
	
	public String deleteDocumentById(String employeeId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(Indecies.INDE_EMPLOYEES).id(employeeId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Employee with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("EmployeeId not found");
        return new StringBuilder("Employee with id " + deleteResponse.id()+" does not exist.").toString();

    }
	
	public List<Employees> searchAllDocuments() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(Indecies.INDE_EMPLOYEES));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, Employees.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<Employees> listOfEmployees = new ArrayList<>();
        for(Hit object : hits){
            System.out.print(((Employees) object.source()));
            listOfEmployees.add((Employees) object.source());
        }
        return listOfEmployees;
    }
}
