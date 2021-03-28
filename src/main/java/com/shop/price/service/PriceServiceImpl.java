package com.shop.price.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.price.dto.ProductDTO;
import com.shop.price.entity.Product;
import com.shop.price.repository.ProductRepository;
import com.shop.price.util.UnitType;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getProducts() {
		return productRepository.findAll().stream().map(p -> new ProductDTO(p.getId(), p.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getUnitTypes() {
		return Arrays.asList(UnitType.values()).stream().map(UnitType::toString).collect(Collectors.toList());
	}

	@Override
	public List<Map<String, Double>> getPriceList() {

		List<Product> productList = productRepository.findAll();
		List<Map<String, Double>> productPriceList = new ArrayList<>();
		Map<String, Double> productPrice;

		for (int i = 1; i <= 50; i++) {

			productPrice = new HashMap<>();
			productPrice.put("units", (double) i);
			for (Product product : productList) {
				productPrice.put(product.getName(), getPriceOfProduct(product, i, UnitType.SINGLE_UNIT));
			}
			productPriceList.add(productPrice);
		}
		return productPriceList;
	}

	@Override
	public double getPrice(int productId, int orderedUnits, UnitType unitType) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if (productOpt.isPresent()) {
			return getPriceOfProduct(productOpt.get(), orderedUnits, unitType);
		} else {
			return -1;
		}
	}

	private double getPriceOfProduct(Product product, int orderedUnits, UnitType unitType) {

		int noOfCarton;
		double finalRemainingUnitsPrice = 0;

		if (unitType.equals(UnitType.SINGLE_UNIT)) {
			noOfCarton = orderedUnits / product.getUnitsPerCarton();

			int noOfRemainingUnits = orderedUnits % product.getUnitsPerCarton();
			double remainingUnitsPrice = noOfRemainingUnits * (product.getCartonPrice() / product.getUnitsPerCarton());
			finalRemainingUnitsPrice = remainingUnitsPrice * 1.3;
		} else {
			noOfCarton = orderedUnits;
		}

		double cartonPrice = noOfCarton * product.getCartonPrice();
		double finalCartonPrice = noOfCarton >= 3 ? cartonPrice * 0.9 : cartonPrice;

		return finalCartonPrice + finalRemainingUnitsPrice;
	}
}
