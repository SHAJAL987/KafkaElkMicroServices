package com.elastic_kafka.api.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.elastic_kafka.api.repository")
@ComponentScan(basePackages = "com.elastic_kafka.api")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration{
	
	//@Value("${elasticsearch.url}")
	public String elasticsearchUrl="https://10.11.200.109:9200";

	@Bean
	@Override
	public RestHighLevelClient elasticsearchClient() {
		final ClientConfiguration config = ClientConfiguration.builder().connectedTo(elasticsearchUrl).build();
		return RestClients.create(config).rest();
	}
}

