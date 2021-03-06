package com.microservice.inventoryservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InventoryController {
	
	@Autowired
	private InventoryItemRepository inventoryItemRepository;
	
	@GetMapping("/api/inventory/{productCode}")
	public ResponseEntity<InventoryItem> findInventoryByProductCode(@PathVariable String productCode) {
		log.info("Finding inventory for product code: "+productCode);
		Optional<InventoryItem> inventoryItem = inventoryItemRepository.findByProductCode(productCode);
		if(inventoryItem.isPresent()) {
			return new ResponseEntity(inventoryItem, HttpStatus.OK);
		} else return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
}
