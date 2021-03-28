package com.shop.price.service;

import java.util.List;
import java.util.Map;

import com.shop.price.dto.ProductDTO;
import com.shop.price.util.UnitType;

public interface PriceService {

	List<ProductDTO> getProducts();

	List<String> getUnitTypes();

	List<Map<String, Double>> getPriceList();

	double getPrice(int product, int orderedUnits, UnitType unitType);

}
