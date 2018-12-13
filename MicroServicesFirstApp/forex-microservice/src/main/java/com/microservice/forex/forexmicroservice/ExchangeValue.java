package com.microservice.forex.forexmicroservice;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class ExchangeValue {
	
	@Id
	private Long id;
	
	@Column(name = "currency_from")
	private String from;
	
	@Column(name = "currency_to")
	private String to;
	
	private int port;
	
	private BigDecimal conversionMultiple;
	
}
