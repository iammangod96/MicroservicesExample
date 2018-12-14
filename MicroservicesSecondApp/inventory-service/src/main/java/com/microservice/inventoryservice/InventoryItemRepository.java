package com.microservice.inventoryservice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
	
	public Optional<InventoryItem> findByProductCode(String code);
	
}
