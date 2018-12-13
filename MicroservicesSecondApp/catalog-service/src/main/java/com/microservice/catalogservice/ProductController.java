package com.microservice.catalogservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<Product> getAllProducts() {
		return productService.findAllProducts();
	}
	
	@GetMapping("/{code}")
	public Product getProductByCode(@PathVariable String code) {
		return productService.findProductByCode(code)
				.orElseThrow(() -> new ProductNotFoundException("Product with code ["+code+"] does not exist"));
	}
	
}
