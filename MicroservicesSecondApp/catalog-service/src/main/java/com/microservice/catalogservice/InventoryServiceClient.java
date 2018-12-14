package com.microservice.catalogservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(commandKey = "inventory-by-code",fallbackMethod="getDefaultProductInventoryByCode")
	public Optional<InventoryResponse> getProductInventoryByCode(String code) {
		ResponseEntity<InventoryResponse> response = restTemplate.getForEntity(
				"http://inventory-service/api/inventory/{code}",
				InventoryResponse.class,
				code
				);
		if(response.getStatusCode() == HttpStatus.OK) {
			return Optional.ofNullable(response.getBody());
		} else {
			log.error("Unable to get inventory for product code : "+code+" "+response.getStatusCode());
			return Optional.empty();
		}
	}
	
	public Optional<InventoryResponse> getDefaultProductInventoryByCode(String code) {
		log.info("Returning default inventory for product code: " + code);
		InventoryResponse response = new InventoryResponse();
		response.setProductCode(code);
		response.setAvailableQuantity(50);
		return Optional.ofNullable(response);
	}
	
}
