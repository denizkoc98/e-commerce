package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.PurchaseDetail;



public interface PurchasedDetailsRepository extends JpaRepository<PurchaseDetail, Integer> {

	
	
	PurchaseDetail findById(int id); 
	List<PurchaseDetail> findByCustomerId(int id); 

}