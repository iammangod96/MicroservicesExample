package com.microservice.catalogservice;

import lombok.Data;

@Data
public class InventoryResponse {
	private String productCode;
	private int availableQuantity;
}
