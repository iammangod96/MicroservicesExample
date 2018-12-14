package com.microservice.catalogservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}
	
	public Optional<Product> findProductByCode(String code) {
		Optional<Product> optionalProduct =  productRepository.findByCode(code);
		if(optionalProduct.isPresent()) {
			log.info("Getting inventory for product code: "+code);
			ResponseEntity<InventoryResponse> inventoryResponse = restTemplate.getForEntity(
					"http://inventory-service/api/inventory/{code}",
                    InventoryResponse.class,
                    code);
			if(inventoryResponse.getStatusCode() == HttpStatus.OK) {
				Integer quantity = inventoryResponse.getBody().getAvailableQuantity();
				log.info("Available quantity: " + quantity);
				optionalProduct.get().setInStock(quantity > 0);
			} else {
				log.error("Unable to get inventory for product code: "+code+inventoryResponse.getStatusCode());
			}
		}
		return optionalProduct;
	}
}
