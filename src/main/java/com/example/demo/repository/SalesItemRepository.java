package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Product;

import com.example.demo.SalesItem;

@Repository
public interface SalesItemRepository extends JpaRepository<SalesItem, Integer> {
	SalesItem findById(int id);

}
