package com.shop.price.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int unitsPerCarton;

	private double cartonPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitsPerCarton() {
		return unitsPerCarton;
	}

	public void setUnitsPerCarton(int unitsPerCarton) {
		this.unitsPerCarton = unitsPerCarton;
	}

	public double getCartonPrice() {
		return cartonPrice;
	}

	public void setCartonPrice(double cartonPrice) {
		this.cartonPrice = cartonPrice;
	}
}
