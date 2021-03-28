package com.shop.price.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.price.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
