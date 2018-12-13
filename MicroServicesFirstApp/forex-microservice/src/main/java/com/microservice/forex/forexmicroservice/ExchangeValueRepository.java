package com.microservice.forex.forexmicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	ExchangeValue findByFromAndTo(String from, String to);
	
}
