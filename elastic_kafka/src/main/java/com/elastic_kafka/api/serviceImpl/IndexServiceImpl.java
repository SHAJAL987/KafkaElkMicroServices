package com.elastic_kafka.api.serviceImpl;

import com.elastic_kafka.api.services.IndexService;

import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.elastic_kafka.api.helper.Indices;
import com.elastic_kafka.api.helper.Util;

import jakarta.annotation.PostConstruct;

public class IndexServiceImpl implements IndexService {
	
	private static final Logger LOG = LoggerFactory.getLogger(Util.class);
	private final List<String> INDICES_TO_CREATE = List.of(Indices.EMPLOYEES_INDEX);
	private RestHighLevelClient restHighLevelClient;
	

	public IndexServiceImpl(RestHighLevelClient restHighLevelClient) {
		this.restHighLevelClient = restHighLevelClient;
	}
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	@Override
	public void tryToCreateIndices() {
		
		final String settings = Util.loadAsString("static/es-settings.json");
		
		for(final String indexName : INDICES_TO_CREATE) {
			try {
				boolean indexExist = restHighLevelClient.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
				if(indexExist) {
					continue;
				}
				
				final String mappings = Util.loadAsString("static/mappings/"+indexName+".json");
				
				if(settings == null || mappings == null) {
					LOG.error("Falid to create with name {}",indexName);
					continue;
				}
				
				final CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
				createIndexRequest.settings(settings, XContentType.JSON);
				createIndexRequest.mapping(settings, XContentType.JSON);
				
				restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
				
			}catch(final Exception e) {
				LOG.error(e.getMessage(),e);
			}
		}
	}

}
