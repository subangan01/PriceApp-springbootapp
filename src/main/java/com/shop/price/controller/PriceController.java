package com.shop.price.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.price.dto.ProductDTO;
import com.shop.price.service.PriceService;
import com.shop.price.util.UnitType;

@RestController
@RequestMapping("/rest")
@CrossOrigin
public class PriceController {

	@Autowired
	private PriceService priceService;

	@GetMapping("/products")
	public List<ProductDTO> getProducts() {
		return priceService.getProducts();
	}

	@GetMapping("/unitTypes")
	public List<String> getUnitTypes() {
		return priceService.getUnitTypes();
	}

	@GetMapping("/priceList")
	public List<Map<String, Double>> getPriceList() {
		return priceService.getPriceList();
	}

	@GetMapping("price/{productId}/{unitType}/{orderedUnits}")
	public double getPrice(@PathVariable int productId, @PathVariable int orderedUnits,
			@PathVariable UnitType unitType) {
		return priceService.getPrice(productId, orderedUnits, unitType);
	}
}
