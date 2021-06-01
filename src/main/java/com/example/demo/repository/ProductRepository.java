package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Product;
import com.example.demo.User;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	Product findById(int id);
}
