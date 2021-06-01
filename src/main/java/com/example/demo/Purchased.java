package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Purchased {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchasedId;
	private int quantityOrdered;
	private String refund;
	

	
	@ManyToOne
	@JsonIgnoreProperties("purchaseditem")
	private Product product;
	
	@ManyToMany(mappedBy="purchaseItem", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("purchase")
    List<PurchaseDetail> pDetails=  new ArrayList<> ();

	public int getPurchasedId() {
		return purchasedId;
	}

	public void setPurchasedId(int purchasedId) {
		this.purchasedId = purchasedId;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	

	

	public String getRefund() {
		return refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	public Purchased(int purchasedId, int quantityOrdered, Product product) {
		super();
		this.purchasedId = purchasedId;
		this.quantityOrdered = quantityOrdered;
		this.product = product;
	}
	
	public Purchased() {
		
	}

	


}