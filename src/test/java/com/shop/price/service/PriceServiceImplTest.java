package com.shop.price.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.shop.price.entity.Product;
import com.shop.price.repository.ProductRepository;
import com.shop.price.util.UnitType;

public class PriceServiceImplTest {

	@InjectMocks
	private PriceServiceImpl priceServiceImpl;

	@Mock
	private ProductRepository productRepository;

	private static final double DELTA = 1e-15;

	private Optional<Product> productPenguinearsOpt;
	private Optional<Product> productHorseshoeOpt;
	private List<Product> productList = new ArrayList<>();
	private int penguinearsId;
	private int horseshoeId;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		Product productPenguinears = new Product();
		penguinearsId = 1;
		productPenguinears.setId(penguinearsId);
		productPenguinears.setName("Penguin-ears");
		productPenguinears.setUnitsPerCarton(20);
		productPenguinears.setCartonPrice(175);
		productPenguinearsOpt = Optional.of(productPenguinears);

		Product productHorseshoe = new Product();
		horseshoeId = 2;
		productHorseshoe.setId(horseshoeId);
		productHorseshoe.setName("Horseshoe");
		productHorseshoe.setUnitsPerCarton(5);
		productHorseshoe.setCartonPrice(825);
		productHorseshoeOpt = Optional.of(productHorseshoe);

		productList.add(productPenguinears);
		productList.add(productHorseshoe);

		when(productRepository.findById(penguinearsId)).thenReturn(productPenguinearsOpt);
		when(productRepository.findById(horseshoeId)).thenReturn(productHorseshoeOpt);

		when(productRepository.findAll()).thenReturn(productList);
	}

	@Test
	public void testGetUnitTypes() {
		Assertions.assertIterableEquals(
				Arrays.asList(UnitType.values()).stream().map(UnitType::toString).collect(Collectors.toList()),
				priceServiceImpl.getUnitTypes());
	}

	@Test
	public void testGetPrice_priceOf_One_Unit_Penguinears() {
		assertEquals(11.375, priceServiceImpl.getPrice(penguinearsId, 1, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Ten_Unit_Penguinears() {
		assertEquals(113.75, priceServiceImpl.getPrice(penguinearsId, 10, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Twenty_Unit_Penguinears() {
		assertEquals(175, priceServiceImpl.getPrice(penguinearsId, 20, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Twentyfive_Unit_Penguinears() {
		assertEquals(231.875, priceServiceImpl.getPrice(penguinearsId, 25, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Sixtyfive_Unit_Penguinears() {
		assertEquals(529.375, priceServiceImpl.getPrice(penguinearsId, 65, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_One_Carton_Penguinears() {
		assertEquals(175, priceServiceImpl.getPrice(penguinearsId, 1, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Two_Carton_Penguinears() {
		assertEquals(350, priceServiceImpl.getPrice(penguinearsId, 2, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Three_Carton_Penguinears() {
		assertEquals(472.5, priceServiceImpl.getPrice(penguinearsId, 3, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Five_Carton_Penguinears() {
		assertEquals(787.5, priceServiceImpl.getPrice(penguinearsId, 5, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_One_Unit_Horseshoe() {
		assertEquals(214.5, priceServiceImpl.getPrice(horseshoeId, 1, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Two_Unit_Horseshoe() {
		assertEquals(429, priceServiceImpl.getPrice(horseshoeId, 2, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Five_Unit_Horseshoe() {
		assertEquals(825, priceServiceImpl.getPrice(horseshoeId, 5, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Six_Unit_Horseshoe() {
		assertEquals(1039.5, priceServiceImpl.getPrice(horseshoeId, 6, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Sixteen_Unit_Horseshoe() {
		assertEquals(2442, priceServiceImpl.getPrice(horseshoeId, 16, UnitType.SINGLE_UNIT), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_One_Carton_Horseshoe() {
		assertEquals(825, priceServiceImpl.getPrice(horseshoeId, 1, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Two_Carton_Horseshoe() {
		assertEquals(1650, priceServiceImpl.getPrice(horseshoeId, 2, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Three_Carton_Horseshoe() {
		assertEquals(2227.5, priceServiceImpl.getPrice(horseshoeId, 3, UnitType.CARTON), DELTA);
	}

	@Test
	public void testGetPrice_priceOf_Five_Carton_Horseshoe() {
		assertEquals(3712.5, priceServiceImpl.getPrice(horseshoeId, 5, UnitType.CARTON), DELTA);
	}

}
