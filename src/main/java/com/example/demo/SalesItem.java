package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class SalesItem {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int salesItemId;
	private int quantity;
	

	@ManyToOne
	@JsonIgnoreProperties("salesi-product")
	private Product product;
	
	@ManyToMany(mappedBy="hasItem")
	@JsonIgnoreProperties("hasItem")
    List<Basket> belongs=  new ArrayList<> ();

	public SalesItem() {
		
	}

	public SalesItem(int salesItemId, int quantity, Product product) {
		super();
		this.salesItemId = salesItemId;
		this.quantity = quantity;
		this.product = product;
	}


	public int getSalesItemId() {
		return salesItemId;
	}


	public void setSalesItemId(int salesItemId) {
		this.salesItemId = salesItemId;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Basket> getBelongs() {
		return belongs;
	}

	public void setBelongs(List<Basket> belongs) {
		this.belongs = belongs;
	}
	
	
}
